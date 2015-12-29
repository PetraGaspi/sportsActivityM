package cz.muni.fi.pa165.sportsactivitymanager.Facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityRecordDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.NewDistanceDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;

import java.util.List;

/**
 *
 * @author Maemi
 */
public interface ActivityRecordFacade {

    void create(ActivityRecordDTO activity);

    List<ActivityRecordDTO> getAllRecords();

    ActivityRecordDTO getRecordById(Long id);

    List<ActivityRecordDTO> getRecordsByUser(UserDTO user);

    List<ActivityRecordDTO> getRecordsByActivity(ActivityDTO activity);

    void changeDistance(NewDistanceDTO newDistance);

    void delete(long id);
    
}
