package cz.muni.fi.pa165.sportsactivitymanager.Entity;

import java.util.Calendar;

/**
 * Created by michal on 10/27/15.
 */
public class ActivityRecord implements IActivityRecord {

    private long id;
    private java.util.Calendar date;
    private double duration;
    private double distance;
    private User user;
    private Activity activity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public double calculateBurnedCalories() {
        return 0;
    }

}
