package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    private static final int USER_ID = START_SEQ;
    private static final int ADMIN_ID = START_SEQ + 1;

    public static final Meal MEAL_USER = new Meal(USER_ID,LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_ADMIN = new Meal(ADMIN_ID,LocalDateTime.of(2018, Month.MAY, 31, 10, 0), "Завтрак", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, (Meal) Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
