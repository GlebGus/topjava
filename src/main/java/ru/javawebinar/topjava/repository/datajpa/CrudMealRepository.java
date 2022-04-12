package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javawebinar.topjava.model.Meal;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id =:id AND m.user.id =:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);


    @Query("SELECT m FROM Meal m WHERE m.user.id =:userId ORDER BY m.dateTime DESC ")
    List<Meal> getAll(@Param("userId") int userId);


    @Query("SELECT m FROM Meal m WHERE m.user.id =:userId AND m.dateTime >=:startDate AND m.dateTime <:endDate ORDER BY m.dateTime DESC ")
    List<Meal> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDateTime, @Param("endDate") LocalDateTime endDateTime, @Param("userId") int userId);
}
