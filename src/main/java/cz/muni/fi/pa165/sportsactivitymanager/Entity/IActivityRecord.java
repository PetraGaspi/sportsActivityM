package cz.muni.fi.pa165.sportsactivitymanager.Entity;

/**
 * Created by michal on 10/27/15.
 */
public interface IActivityRecord {
    /**
     * Calculates the burned calories according to ActivityRecord's attributes' values
     * @return burned calories in kj
     */
    double calculateBurnedCalories();
}
