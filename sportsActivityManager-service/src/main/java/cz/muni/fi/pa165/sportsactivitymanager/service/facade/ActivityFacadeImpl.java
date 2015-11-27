/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.service.facade;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityService;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juraj Pleško, 359530
 */
@Service
@Transactional
public class ActivityFacadeImpl implements ActivityFacade {
    
    @Autowired
        private ActivityService service;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void createActivity (ActivityDTO activity){
        
        service.createActivity(service.getActivityById(activity.getId()));
            };
    
    @Override
    public void deleteActivity (ActivityDTO activity){
        service.deleteActivity(service.getActivityById(activity.getId()));
    };
    
    @Override
    public void updateActivity (ActivityDTO activity){
        service.updateActivity(service.getActivityById(activity.getId()));
    };    
    
    @Override
    public ActivityDTO findActivityById(long id){
        return beanMappingService.mapTo(service.getActivityById(id), ActivityDTO.class);
    };
    
    @Override
    public List<ActivityDTO> findAllActivities(){
        return beanMappingService.mapTo(service.findAllActivities(), ActivityDTO.class);
    };
    
    @Override
    public List<ActivityDTO> findAllDistance(){
        return beanMappingService.mapTo(service.findAllDistance(), ActivityDTO.class);
    };
    
    @Override
    public List<ActivityDTO> findAllNonDistance(){
        return beanMappingService.mapTo(service.findAllNonDistance(), ActivityDTO.class);
    };
    
    @Override
    public List<ActivityDTO> findByName(String name){
        return beanMappingService.mapTo(service.findByName(name), ActivityDTO.class);
    };
}
