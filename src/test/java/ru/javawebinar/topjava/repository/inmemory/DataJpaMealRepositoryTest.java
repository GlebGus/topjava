package ru.javawebinar.topjava.repository.inmemory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceTest;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealRepositoryTest extends MealServiceTest {
    @Autowired
MealService mealService;
    @Test
    public void getMealWithUser(){
        Meal getMealWithUser = mealService.getMealWithUser(MEAL1_ID, USER_ID);
        Meal meal = meal1;
        meal.setUser(UserTestData.user);
        MEAL_MATCHER.assertMatch(getMealWithUser, meal);
    }
}
