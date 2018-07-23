package com.lodge.Workout.Model.data;


import com.lodge.Workout.Model.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentDao extends CrudRepository<Comments, String> {
}
