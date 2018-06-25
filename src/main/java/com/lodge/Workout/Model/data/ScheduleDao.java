package com.lodge.Workout.Model.data;

import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface ScheduleDao extends CrudRepository<Schedule, Integer> {
}
