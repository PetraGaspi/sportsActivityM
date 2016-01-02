package cz.muni.fi.pa165.sportsactivitymanager.Facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;

import java.util.List;

/**
 * @author Juraj Pleško
 */
public interface ActivityFacade {

    long createActivity(ActivityCreateDTO activity);

    void deleteActivity(ActivityDTO activity);

    void updateActivity(ActivityDTO activity);

    ActivityDTO findActivityById(long id);

    List<ActivityDTO> getAllActivities();

    List<ActivityDTO> findAllDistance();

    List<ActivityDTO> findAllNonDistance();

    List<ActivityDTO> findByName(String name);
}
