package cz.muni.fi.pa165.sportsactivitymanager.Facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.CaloriesDTO;

/**
 *
 * @author Juraj Pleško
 */
public interface CaloriesFacade {
    
    
    public void createCalories(CaloriesDTO ca);
    
    void updateCalories(CaloriesDTO ca);
    
    void deleteCalories(CaloriesDTO ca);
    
    CaloriesDTO getCaloriesById(long id);
}
