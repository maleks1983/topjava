package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }


    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(100002, 100000);
    }

    @Test
    public void delete() {
        service.delete(100002, 100000);
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(100000);
    }

    @Test
    public void update() {
        Meal meal = new Meal(100002,LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
        meal.setCalories(5000);
        service.update(meal,100000);
    }

    @Test
    public void create() {
        Meal meal = new Meal(null,LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal newMeal = service.create(meal, 100000);
        meal.setId(newMeal.getId());


    }
}