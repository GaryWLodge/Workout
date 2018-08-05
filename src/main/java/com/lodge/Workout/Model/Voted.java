package com.lodge.Workout.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Voted {

    @Id
    @GeneratedValue
    private int id;

    private boolean hasvoted;

    @ManyToOne
    private User user;

    @ManyToOne
    private Schedule schedule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasvoted() {
        return hasvoted;
    }

    public void setHasvoted(boolean hasvoted) {
        this.hasvoted = hasvoted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
