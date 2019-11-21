package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    // null if not found, when updated
    Meal save(int userid,Meal meal);

    // false if not found
    boolean delete(int userid, int id);

    // null if not found
    Meal get(int userid, int id);

    Collection<Meal> getAll(int userid);
}
