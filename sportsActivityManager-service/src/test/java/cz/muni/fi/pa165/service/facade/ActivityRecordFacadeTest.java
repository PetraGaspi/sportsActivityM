package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.ActivityDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enum.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.ActivityRecordFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityRecordService;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.service.facade.ActivityRecordFacadeImpl;
import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityRecordFacadeTest {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private ActivityRecordService recordService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ActivityRecordFacade recordFacade = new ActivityRecordFacadeImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private ActivityRecord record = new ActivityRecord();

    private User user;

    private ActivityDTO recordDTO;

    private Activity activity;

    @BeforeMethod
    public void setUpMethod() {
        user = new User();
        user.setName("User Name");
        user.setAge(22);
        user.setEmail("test@java.fi");
        user.setSex(Sex.Male);
        user.setWeight(85.4);

        Calories calories = new Calories();
        calories.setIndex(12.5);

        activity = new Activity();
        activity.setCalories(calories);
        activity.setMeasureDistance(false);
        activity.setName("Volleyball");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        //sets the record date to current day minus 2

        record.setDate(calendar);
        record.setActivity(activity);
        record.setDuration(60);
        record.setUser(user);
    }

    @Test
    public void testGetRecordById() throws Exception {
        recordService.createRecord(record);

        recordFacade.getRecordById(record.getId());
        when(recordService.getActivityRecordById(record.getId())).thenReturn(record);
    }

}