package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

@Controller
public class MealRestController extends AbstractMealController {


    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Meal get(int id) {
        return super.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return super.getAll();
    }

    @Override
    public void save(Meal meal) {
        super.save(meal);
    }

}