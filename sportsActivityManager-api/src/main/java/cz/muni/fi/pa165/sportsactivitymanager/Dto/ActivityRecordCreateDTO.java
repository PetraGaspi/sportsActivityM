package cz.muni.fi.pa165.sportsactivitymanager.Dto;

import java.util.Calendar;

/**
 * Created by michal on 1/20/16.
 */
public class ActivityRecordCreateDTO {
    private java.util.Calendar date;

    private double duration;

    private double distance;

    private long userId;

    private long activityId;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


}
