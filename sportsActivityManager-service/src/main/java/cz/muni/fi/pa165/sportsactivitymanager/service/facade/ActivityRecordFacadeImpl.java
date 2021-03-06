package cz.muni.fi.pa165.sportsactivitymanager.service.facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityRecordDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.NewDistanceDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityRecordFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityRecordService;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityService;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by michal on 11/21/15.
 */
@Service
@Transactional
public class ActivityRecordFacadeImpl implements ActivityRecordFacade {

    final static org.slf4j.Logger log = LoggerFactory.getLogger(ActivityRecordFacadeImpl.class);

    @Autowired
    private ActivityRecordService service;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public long create(ActivityRecordDTO activityRecordDTO) {
        log.debug("create DTO has calories: "+activityRecordDTO.getActivity().getCalories().toString());
        log.debug("create DTO has calories w id: " + activityRecordDTO.getActivity().getCalories().getId());

        log.debug("create is getting DTO: "+activityRecordDTO);
        log.debug("create passes to service: "+beanMappingService.mapTo(activityRecordDTO, ActivityRecord.class).toString());

        return service.createRecord(beanMappingService.mapTo(activityRecordDTO, ActivityRecord.class));
    }

    @Override
    public List<ActivityRecordDTO> getAllRecords() {
        final List<ActivityRecord> allRecords = service.getAllRecords();
        final List<ActivityRecordDTO> dtos = beanMappingService.mapTo(allRecords, ActivityRecordDTO.class);
        return dtos;
    }

    @Override
    public ActivityRecordDTO getRecordById(Long id) {
        return beanMappingService.mapTo(service.getActivityRecordById(id), ActivityRecordDTO.class);
    }

    @Override
    public List<ActivityRecordDTO> getRecordsByUser(UserDTO user) {
        final List<ActivityRecord> allRecords = service.getRecordsByUser(userService.getUserById(user.getId()));
        final List<ActivityRecordDTO> dtos = beanMappingService.mapTo(allRecords, ActivityRecordDTO.class);
        return dtos;
    }

    @Override
    public List<ActivityRecordDTO> getRecordsByActivity(ActivityDTO activity) {
        final List<ActivityRecord> allRecords = service.getRecordsByActivity(activityService.getActivityById(activity.getId()));
        final List<ActivityRecordDTO> dtos = beanMappingService.mapTo(allRecords, ActivityRecordDTO.class);
        return dtos;
    }

    @Override
    public void changeDistance(NewDistanceDTO newDistance) {
        ActivityRecord changedRecord = service.getActivityRecordById(newDistance.getRecordId());
        changedRecord.setDistance(newDistance.getNewValue());
        service.update(changedRecord);
    }

    @Override
    public void delete(long activityRecordId) {
        ActivityRecord deleteTemplate = new ActivityRecord();
        deleteTemplate.setId(activityRecordId);

        service.delete(deleteTemplate);
    }

    @Override
    public List<ActivityRecordDTO> getRecordsLastDays(int days) {
        List<ActivityRecord> records = service.getActivityRecordsLastDays(days);
        final List<ActivityRecordDTO> recordsDTO = beanMappingService.mapTo(records, ActivityRecordDTO.class);

        return recordsDTO;
    }
}
