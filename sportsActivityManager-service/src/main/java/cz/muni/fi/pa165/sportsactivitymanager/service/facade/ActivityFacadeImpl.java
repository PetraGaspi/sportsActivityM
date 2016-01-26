/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.service.facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityService;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Juraj Pleško, 359530
 */
@Service
@Transactional
public class ActivityFacadeImpl implements ActivityFacade {

    final static org.slf4j.Logger log = LoggerFactory.getLogger(ActivityRecordFacadeImpl.class);

    @Autowired
    private ActivityService service;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public long createActivity(ActivityCreateDTO activityDTO) {
        Calories createC = new Calories();
        createC.setIndex(activityDTO.getCalories());

        Activity createA = new Activity();

        createA.setCalories(createC);
        createA.setMeasureDistance(activityDTO.getMeasureDistance());
        createA.setName(activityDTO.getName());

        service.createActivity(createA);
        return createA.getId();
    }

    @Override
    public void deleteActivity(ActivityDTO activity) {
        service.deleteActivity(service.getActivityById(activity.getId()));
    }

    @Override
    public void updateActivity(ActivityDTO activity) {
        service.updateActivity(service.getActivityById(activity.getId()));
    }

    @Override
    public ActivityDTO findActivityById(long id) {
        log.debug("activityDTO id: "+id);
        log.debug("activity POM id:"+beanMappingService.mapTo(service.getActivityById(id), ActivityDTO.class).getId());
        log.debug("findActivityById getting object: "+service.getActivityById(id).toString());
        log.debug("findActivityById returning object: "+beanMappingService.mapTo(service.getActivityById(id), ActivityDTO.class));

        return beanMappingService.mapTo(service.getActivityById(id), ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        return beanMappingService.mapTo(service.findAllActivities(), ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> findAllDistance() {
        return beanMappingService.mapTo(service.findAllDistance(), ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> findAllNonDistance() {
        return beanMappingService.mapTo(service.findAllNonDistance(), ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> findByName(String name) {
        return beanMappingService.mapTo(service.findByName(name), ActivityDTO.class);
    }
}
