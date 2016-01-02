package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import org.springframework.stereotype.Service;

/**
 * @author Juraj Pleško, 359530
 */
@Service
public interface CaloriesService {

    public void createCalories(Calories ca);

    void updateCalories(Calories ca);

    void deleteCalories(Calories ca);

    Calories getCaloriesById(long id);

}
