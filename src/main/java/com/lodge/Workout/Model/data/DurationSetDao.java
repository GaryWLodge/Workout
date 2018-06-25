package com.lodge.Workout.Model.data;

import com.lodge.Workout.Model.DurationSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DurationSetDao extends CrudRepository<DurationSet, Integer> {
}
