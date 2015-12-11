/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.service.facade;

import cz.muni.fi.pa165.sportsactivitymanager.Facade.CaloriesFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import cz.muni.fi.pa165.sportsactivitymanager.service.CaloriesService;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.CaloriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author Juraj Pleško, 359530
 */
@Service
@Transactional
public class CaloriesFacadeImpl implements CaloriesFacade {
    
    @Autowired
        private CaloriesService service;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    public void createCalories(CaloriesDTO ca){
        service.createCalories(service.getCaloriesById(ca.getId()));
    };
    
    public void updateCalories(CaloriesDTO ca){
        service.updateCalories(service.getCaloriesById(ca.getId()));
    };
    
    public void deleteCalories(CaloriesDTO ca){
        service.deleteCalories(service.getCaloriesById(ca.getId()));
    };
    
    public CaloriesDTO getCaloriesById(long id){
        return beanMappingService.mapTo(service.getCaloriesById(id), CaloriesDTO.class);
    };
    
}
