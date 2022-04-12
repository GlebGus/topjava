package ru.javawebinar.topjava.repository.inmemory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserRepositoryTest extends UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void getWithMeals(){
        User getWithMeals = userService.getWithMeals(USER_ID);
        User newUser = user;
        newUser.setMeals(MealTestData.meals);
        USER_MATCHER.assertMatch(getWithMeals, newUser);
    }
}
