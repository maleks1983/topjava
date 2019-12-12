package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;


    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        meal.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(meal);

    }

    @Override
    public boolean delete(int id, int userId) {
        int i=crudRepository.deleteByUser_idAndId(userId, id);
        return i != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal=crudRepository.getMealByUser_IdAndId(userId, id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findMealsByUser_IdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {

        if (startDate != null && endDate != null) {
            return crudRepository.findAllByUser_IdAndDateTimeBetweenOrderByDateTimeDesc(
                    userId, LocalDateTime.of(startDate, LocalTime.MIN),
                    LocalDateTime.of(endDate, LocalTime.of(23, 59)));
        } else return crudRepository.findMealsByUser_IdOrderByDateTimeDesc(userId);
    }


}
