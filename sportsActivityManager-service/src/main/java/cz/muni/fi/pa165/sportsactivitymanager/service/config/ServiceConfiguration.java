package cz.muni.fi.pa165.sportsactivitymanager.service.config;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.sportsactivitymanager.PersistenceSampleApplicationContext;

import cz.muni.fi.pa165.sportsactivitymanager.service.UserServiceImpl;
import cz.muni.fi.pa165.sportsactivitymanager.service.facade.UserFacadeImpl;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={UserServiceImpl.class, UserFacadeImpl.class})
public class ServiceConfiguration {
	
	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}
	
	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        mapping(User.class, UserDTO.class);
	    }
	}

}