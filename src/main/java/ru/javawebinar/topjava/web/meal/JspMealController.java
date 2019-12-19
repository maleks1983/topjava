package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
public class JspMealController  extends AbstractMealRestController{

    public JspMealController(MealService service) {
        super(service);
    }


    @GetMapping("/meals")
    public String Meals(Model model) {
        model.addAttribute("meals",getAll());
        return "meals";
    }

    @PostMapping("meals/update:{id}")
    public String Update(@PathVariable int id, Model model) {
        model.addAttribute("meals", get(id));
        return "mealForm";
    }

    @PostMapping("meals/save")
    public String Save(@ModelAttribute("meal") Meal meal) {
        if (meal.isNew()) {
            create(meal);
        } else {
            update(meal, meal.getId());
        }
        return "redirect:/meals";
    }

    @PostMapping("meals/create")
    public String Create(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meals", meal);
        return "mealForm";
    }


    @GetMapping("meals/delete:{id}")
    public String Delete(@PathVariable int id) {
        delete(id);
        return "redirect:/meals";
    }

    @GetMapping("meals/filter")
    public String filter(@ModelAttribute("startDate") String sd,
                         @ModelAttribute("endDate") String ed,
                         @ModelAttribute("startTime") String st,
                         @ModelAttribute("endTime") String et, Model model) {
        model.addAttribute(getBetween(LocalDate.parse(sd),LocalTime.parse(st),LocalDate.parse(ed),LocalTime.parse(et)));
        return "meals";
    }

}
