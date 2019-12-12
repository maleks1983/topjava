package ru.javawebinar.topjava.repository.datajpa;

import org.hibernate.sql.Delete;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional (readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findMealsByUser_IdOrderByDateTimeDesc(Integer userid);

    List<Meal> findAllByUser_IdAndDateTimeBetweenOrderByDateTimeDesc(Integer user_id, @NotNull LocalDateTime dateTime, @NotNull LocalDateTime dateTime2);

    Meal getMealByUser_IdAndId(Integer user_id, Integer id);

    @Transactional
    int deleteByUser_idAndId(Integer userid, Integer id);
}