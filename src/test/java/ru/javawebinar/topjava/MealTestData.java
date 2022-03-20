package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
  public static final int USER_ID = START_SEQ;
  public static final int ADMIN_ID = START_SEQ +1;
  public static final int MEAL_ID = START_SEQ +3;
    public static final Meal meal = new Meal(START_SEQ + 3,LocalDateTime.of(2020, 1, 30, 7, 0),
            "Завтрак", 800);
    public static final Meal meal2 = new Meal(START_SEQ + 4,LocalDateTime.of(2020, 1, 30, 12, 0),
            "Обед", 1000);
    public static final Meal meal3 = new Meal(START_SEQ + 5,LocalDateTime.of(2020, 1, 31, 7, 0),
            "Завтрак", 800);
    public static final Meal meal4 = new Meal(START_SEQ + 6,LocalDateTime.of(2020, 1, 31, 12, 0),
            "Обед", 1000);
    public static Meal getNew(){
        return new Meal(null, LocalDateTime.of(2000, 12, 31, 12,0), "Описание",1555);
    }
    public static Meal getUpdated(){
        Meal updated = new Meal(meal);
        updated.setDateTime(LocalDateTime.of(2020,12,31,12,0));
        updated.setDescription("Новое описание");
        updated.setCalories(800);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
    public static void assertMatch(Iterable<Meal> actual, Meal... expected){
        assertMatch(actual, Arrays.asList(expected));
    }public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected){
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
