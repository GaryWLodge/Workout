package com.lodge.Workout.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @ManyToMany
    private List<Workout> workouts = new ArrayList<>();

    public void addItem(Workout item) {
        workouts.add(item);
    }

    public void addComment(Comments item) {
        comments.add(item);
    }

    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<Comments> comments;

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser(User u) {
        return user;
    }

    public List<Comments> getComments() {
        return comments;
    }

}
