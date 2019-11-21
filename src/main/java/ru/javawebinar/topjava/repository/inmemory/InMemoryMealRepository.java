package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        int coun=0;
        int userid=0;
        for (Meal item : MealsUtil.MEALS) {
           if (coun==3){
               userid++;
           }
            save(userid, item);
           coun++;
        }
    }

    @Override
    public Meal save(int userid, Meal meal) {
        Map<Integer, Meal> temp =repository.get(userid)==null?new HashMap<>():repository.get(userid);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            temp.put(meal.getId(), meal);
            repository.put(userid, temp);
            return meal;
        }
        // treat case: update, but not present in storage
        temp.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repository.put(userid, temp);
        return temp.get(meal.getId());
    }

    @Override
    public boolean delete(int userid, int id) {
        return repository.get(userid).computeIfPresent(id,
                (key,velue)->repository.get(userid).remove(key))!=null;
    }

    @Override
    public Meal get(int userid, int id) {
        return repository.get(userid).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userid) {
        return repository.get(userid).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

