package com.lodge.Workout.Model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15 , message = "min 3 max 15")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min=5, message = "min 5")
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Workout> workouts;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Schedule> schedules;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<DurationSet> durationsets;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Voted> votes;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Workout> getWorkouts() {return workouts;}

    public List<Schedule> getSchedules() {return schedules;}

    public List<DurationSet> getDurationSets() {return durationsets;}

    public List<Voted> getVotes() {
        return votes;
    }

    public void setVotes(List<Voted> votes) {
        this.votes = votes;
    }
}
