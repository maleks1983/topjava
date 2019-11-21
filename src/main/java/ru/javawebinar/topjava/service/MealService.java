package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

@Service
public class MealService {

    private MealRepository repository;
    @Autowired
    public MealService(MealRepository repository){this.repository = repository;}


    public boolean delete(Integer userid, int id){
       return repository.delete(userid,id);
    }

    public Meal get(Integer userid, int id){
        return repository.get(userid,id);
    }

    public Collection<Meal> getAll(Integer userid){
        return repository.getAll(userid);
    }
    public void save(Integer userid, Meal meal){
        repository.save(userid,meal);
    }

}
