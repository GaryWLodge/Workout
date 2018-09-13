package com.lodge.Workout.Model.data;

import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.Voted;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public interface VotedDao extends CrudRepository<Voted, Integer> {
    public List<Voted> findAllByUserUsername(String username);
}
