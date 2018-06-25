package com.lodge.Workout.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Workout {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String notes;

    @ManyToOne
    private DurationSet durationset;

    @ManyToMany(mappedBy = "workouts")
    private List<Schedule> schedules;

    public Workout(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }
    @ManyToOne
    private User user;

    public Workout() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DurationSet getDurationSet() {
        return durationset;
    }

    public void setDurationSet(DurationSet durationset) {
        this.durationset = durationset;
    }
    public void setUser(User u) {this.user = u;}
}
