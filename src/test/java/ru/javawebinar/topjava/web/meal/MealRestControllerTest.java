package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;

public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    private MealService mealService;
    @Test
    void get() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(meal1));
    }
@Test
   void delete() throws Exception{
    perform(MockMvcRequestBuilders.delete(REST_URL + MEAL1_ID))
            .andDo(print())
            .andExpect(status().isNoContent());
    assertThrows(NotFoundException.class, () -> mealService.get(MEAL1_ID, USER_ID));

    }
    @Test
    void getBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        MEAL_MATCHER.assertMatch(mealService.getBetweenInclusive(LocalDate.of(2020, Month.JANUARY, 30),
                LocalDate.of(2020, Month.JANUARY, 30), USER_ID), meal3, meal2, meal1);
    }
    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_TO_MATCHER.contentJson(getTos(meals, 2000)));
    }
@Test
   void update() throws Exception{
 Meal updated = MealTestData.getUpdated();
 perform(MockMvcRequestBuilders.put(REST_URL + MEAL1_ID)
         .contentType(MediaType.APPLICATION_JSON)
         .content(JsonUtil.writeValue(updated)))
         .andExpect(status().isNoContent());
 MEAL_MATCHER.assertMatch(mealService.get(MEAL1_ID, USER_ID), updated);
    }
@Test
    void createWithLocation() throws Exception{
       Meal newMeal = MealTestData.getNew();
    ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.writeValue(newMeal)))
            .andExpect(status().isCreated());

    Meal created = MEAL_MATCHER.readFromJson(action);
    int newId = created.id();
    newMeal.setId(newId);
    MEAL_MATCHER.assertMatch(created, newMeal);
    MEAL_MATCHER.assertMatch(mealService.get(newId, USER_ID), created);

    }

    public List<MealTo> getBetween(LocalDate of, LocalTime of1, LocalDate of2, LocalTime of3) {
        return null;
    }
}
