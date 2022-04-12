package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private final CrudUserRepository crudUserRepository;
    private final CrudMealRepository crudMealRepository;

    public DataJpaMealRepository(CrudUserRepository crudUserRepository, CrudMealRepository crudRepository) {
        this.crudUserRepository = crudUserRepository;
        this.crudMealRepository = crudRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        //исправить?
        meal.setUser(crudUserRepository.getById(userId));
        if (meal.isNew()) {
            return crudMealRepository.save(meal);
        } else if (!meal.isNew() && get(meal.id(), userId) == null) {
            return null;
        }
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudMealRepository.findById(id).filter(meal -> meal.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudMealRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }

    @Transactional
    public Meal getMealWithUser(int id, Integer userId) {
        Meal meal = get(id, userId);
        meal.setUser(crudUserRepository.getById(userId));
        return meal;
    }
}
