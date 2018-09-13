package com.lodge.Workout.Model.data;

import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.User;
import com.lodge.Workout.Model.Voted;
import com.lodge.Workout.Model.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface ScheduleDao extends CrudRepository<Schedule, Integer> {
    public List<Schedule> findById(int id);
    public List<Schedule> findAllByOrderByVoteDesc();
    public List<Schedule> findAllByVotes(Iterable voted);

}
