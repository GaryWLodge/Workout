package com.lodge.Workout.Model.forms;

import com.lodge.Workout.Model.Schedule;
import com.lodge.Workout.Model.Workout;

import javax.validation.constraints.NotNull;

public class AddScheduleItemForm {

    @NotNull
    private int scheduleId;

    @NotNull
    private int workoutId;

    private Schedule schedule;

    private Iterable<Workout> workouts;

    public AddScheduleItemForm() {}

    public AddScheduleItemForm(Iterable<Workout> workouts, Schedule schedule) {
        this.workouts = workouts;
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Iterable<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Iterable<Workout> Workouts) {
        this.workouts = workouts;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}

