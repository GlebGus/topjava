package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(MEAL_ID, USER_ID);
        assertMatch(meal, meal);
    }

    @Test
   public void delete(){
        mealService.delete(MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID, USER_ID));
    }
    @Test
    public void getBetweenInclusive() {
        Meal meal = mealService.get(MEAL_ID, USER_ID);
        List<Meal> getBetween = mealService.getBetweenInclusive(LocalDate.of(2018, 2, 2), LocalDate.of(2022, 2, 2), USER_ID);
        assertMatch(getBetween,meal,meal2);
    }
    @Test
    public void getAll(){
        List<Meal> all = mealService.getAll(USER_ID);
        assertMatch(all, meal,meal2);
    }
    @Test
    public void update(){
        Meal updated = getUpdated();
        mealService.update(updated, USER_ID);
        assertMatch(mealService.get(MEAL_ID,USER_ID), getUpdated());
    }
        @Test
    public void create(){
        Meal created = mealService.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal =getNew();
        newMeal.setId(newId);
        assertMatch(created,newMeal);
        assertMatch(mealService.get(newId,USER_ID),newMeal);
        }
}