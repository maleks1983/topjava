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
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealRestController {

    public JspMealController(MealService service) {
        super(service);
    }


    @GetMapping()
    public String Meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }


    @PostMapping("/update/{id}")
    public String Update(@PathVariable int id, Model model) {
        model.addAttribute("meals", get(id));
        return "mealForm";
    }

    @PostMapping("/save")
    public String Save(@ModelAttribute("meal") Meal meal) {
        if (meal.isNew()) {
            create(meal);
        } else {
            update(meal, meal.getId());
        }
        return "redirect:/meals";
    }

    @PostMapping("/create")
    public String Create(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meals", meal);
        return "mealForm";
    }


    @GetMapping("/delete/{id}")
    public String Delete(@PathVariable int id) {
        delete(id);
        return "redirect:/meals";
    }

    @PostMapping()
    public String filter(@ModelAttribute("startDate") String startDate,
                         @ModelAttribute("endDate") String endDate,
                         @ModelAttribute("startTime") String startTime,
                         @ModelAttribute("endTime") String endTime, Model model) {
        LocalDate sd = DateTimeUtil.parseLocalDate(startDate);
        LocalTime st = DateTimeUtil.parseLocalTime(startTime);
        LocalDate ed = DateTimeUtil.parseLocalDate(endDate);
        LocalTime et = DateTimeUtil.parseLocalTime(endTime);
        model.addAttribute("meals", getBetween(sd, st, ed, et));
        return "meals";
    }
}
