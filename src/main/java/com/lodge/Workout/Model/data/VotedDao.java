package com.lodge.Workout.Model.data;

import com.lodge.Workout.Model.Voted;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional

public interface VotedDao extends CrudRepository<Voted, Integer> {
}
