package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealUIController extends AbstractMealController{
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

  @PostMapping
    public Meal create(@RequestParam String date, @RequestParam String description,@RequestParam int calories) {
        return super.create(new Meal(null, LocalDateTime.parse(date),description,calories));
    }

    @Override
    @GetMapping("/filter")
    public List<MealTo> getBetween(@Nullable @RequestParam LocalDate startDate,@Nullable @RequestParam  LocalTime startTime,@Nullable @RequestParam  LocalDate endDate,@Nullable @RequestParam  LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
