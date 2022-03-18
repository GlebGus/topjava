package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
@Service
public class MealService {
@Autowired
    private MealRepository repository;
    public Meal create(Meal meal){
        return repository.save(meal);
    }

    public void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(Integer id){
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Meal> getMeals(Integer userId){
        List<Meal> meals = new ArrayList<>();
        repository.getAll().forEach(meal -> {
            if(meal.getUserId() == userId){
                meals.add(meal);
            }
        });
        return meals;
    }

    public void update(Meal meal){
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }
}