package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import org.slf4j.Logger;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;


    public List<Meal> getAll(int userId){
        Objects.requireNonNullElse(userId, SecurityUtil.authUserId());
        return service.getMeals(userId);
    }

    public Meal get(Integer id){
        log.debug("get");
        return service.get(id);
    }

    public Meal create(Meal meal){
        return service.create(meal);
    }

    public void delete(Integer id){
        service.delete(id);
    }

    public void update(Integer id, Meal meal){
        assureIdConsistent(meal, id);
        service.update(meal);
    }
}