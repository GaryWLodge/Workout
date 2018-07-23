package com.lodge.Workout.Model.forms;

import com.lodge.Workout.Model.Comments;
import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.Workout;

import javax.validation.constraints.NotNull;

public class AddCommentForm {

    @NotNull
    private int scheduleId;

    @NotNull
    private int commentId;

    private Schedule schedule;

    private Iterable<Comments> comments;

    public AddCommentForm() {}

    public AddCommentForm(Iterable<Comments> comments, Schedule schedule) {
        this.comments = comments;
        this.schedule = schedule;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Iterable<Comments> getComments() {
        return comments;
    }

    public void setComments(Iterable<Comments> comments) {
        this.comments = comments;
    }
}
