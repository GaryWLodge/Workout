package com.lodge.Workout.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DurationSet {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn(name = "durationset_id")
    private List<Workout> workouts = new ArrayList<>();

    public DurationSet(String name) {
        this.name = name;
    }

    @ManyToOne
    private User user;

    public DurationSet() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User u) {this.user = u;}



}
