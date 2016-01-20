package cz.muni.fi.pa165.sportsactivitymanager.Entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author Michal Stefanik 422237
 */
@Entity
public class ActivityRecord implements Comparable<ActivityRecord> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar date;

    @DecimalMin("0.0")
    @NotNull
    private double duration;

    @DecimalMin("0.0")
    private double distance;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityRecord)) return false;

        ActivityRecord that = (ActivityRecord) o;

        if (Double.compare(that.getDistance(), distance) != 0) return false;
        if (Double.compare(that.getDuration(), duration) != 0) return false;
        if (id != that.id) return false;
        if (!activity.equals(that.getActivity())) return false;
        if (!date.equals(that.getDate())) return false;
        if (!user.equals(that.getUser())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(id);
        return hash;
    }

    /**
     * Calculates the burned calories according to ActivityRecord's attributes' values
     *
     * @return burned calories in kj
     */
    public double calculateBurnedCalories() {
        return duration * activity.getCalories().getIndex();
    }

    @Override
    public int compareTo(ActivityRecord other) {
        return (-1) * date.compareTo(other.date);
    }
}
