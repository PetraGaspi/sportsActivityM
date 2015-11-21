//package cz.muni.fi.pa165.sportsactivitymanager.service.config;
//
//import cz.muni.fi.pa165.sportsactivitymanager.PersistenceSampleApplicationContext;
//import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityRecordService;
//import cz.muni.fi.pa165.sportsactivitymanager.service.facade.ActivityRecordServiceFacadeImpl;
//import org.dozer.DozerBeanMapper;
//import org.dozer.Mapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//@Configuration
//@Import(PersistenceSampleApplicationContext.class)
//@ComponentScan(basePackageClasses={ActivityRecordService.class, ActivityRecordServiceFacadeImpl.class})
//public class ServiceConfiguration {
//
//
//	@Bean
//	public Mapper dozer(){
//		DozerBeanMapper dozer = new DozerBeanMapper();
////		dozer.addMapping(new DozerCustomConfig());
//		return dozer;
//	}
////
////	/**
////	 * Custom config for Dozer if needed
////	 * @author nguyen
////	 *
////	 */
////	public class DozerCustomConfig extends BeanMappingBuilder {
////	    @Override
////	    protected void configure() {
////	        mapping(Category.class, CategoryDTO.class);
////	    }
////	}
////
//}
//
