package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityRecordDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enum.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityRecordService;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Michal Stefanik
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordServiceTest extends AbstractTransactionalTestNGSpringContextTests{

    private ActivityRecord record = new ActivityRecord();
    private User user;
    private Activity activity;

    @Mock
    private ActivityRecordDAO recordDAO;

    @Mock
    private ActivityDAO activityDAO;
    
    @Autowired
    @InjectMocks
    private ActivityRecordService service;
    
    @BeforeClass
    public void setup() throws ServiceException{
         MockitoAnnotations.initMocks(this);
    }

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

        activityDAO.create(activity);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        //sets the record date to current day minus 2

        record.setDate(calendar);
        record.setActivity(activity);
        record.setDuration(60);
        record.setUser(user);
    }

    @Test
    public void testCreateRecord() {
        service.createRecord(record);
        verify(recordDAO).create(record);
    }

    @Test
    public void testGetActivityRecordById() {
        when(recordDAO.getRecordById(record.getId())).thenReturn(record);
        Assert.assertEquals(record, service.getActivityRecordById(record.getId()));
    }

    @Test
    public void testDelete() {
        service.delete(record);
        service.createRecord(record);

        verify(recordDAO).delete(record);
    }

    @Test
    public void testGetRecordsByUser() {
        service.getRecordsByUser(user);
        when(recordDAO.getRecordsByUser(user)).thenReturn(Arrays.asList(record));

        User user2 = new User();
        user2.setName("Jozef Kalerab");
        user2.setAge(21);
        user2.setSex(Sex.Female);
        user2.setWeight(185.0);

        record.setUser(user2);
        recordDAO.update(record);

        when(recordDAO.getRecordsByUser(user2)).thenReturn(Arrays.asList(record));
    }

    @Test
    public void testGetRecordsByActivity() {
        service.getRecordsByActivity(activity);
        when(recordDAO.getRecordsByActivity(activity)).thenReturn(Arrays.asList(record));

        Activity activity2 = new Activity();
        activity2.setCalories(new Calories());
        activity2.setMeasureDistance(false);
        activity2.setName("Squash");
        activityDAO.create(activity2);

        record.setActivity(activity2);
        recordDAO.update(record);

        when(recordDAO.getRecordsByActivity(activity2)).thenReturn(Arrays.asList(record));
    }

    @Test
    public void testGetActivityRecordsLastDays() {
        activityDAO.create(activity);
        record.setActivity(activity);
        service.createRecord(record);
        when(service.getActivityRecordsLastDays(activity, 3)).thenReturn(Arrays.asList(record));

        Calendar newDate = Calendar.getInstance();
        newDate.add(Calendar.DAY_OF_MONTH,-5);
        record.setDate(newDate);
        recordDAO.update(record);

        when(service.getActivityRecordsLastDays(activity, 3)).thenReturn(Arrays.asList());
    }
}
