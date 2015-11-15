import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityRecordDao;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.Enum.Sex;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import cz.muni.fi.pa165.sportsactivitymanager.PersistenceSampleApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;


/**
 *
 * @author Petra Gasparikova
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityRecordDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    public EntityManager em;
    
    @Autowired
    public ActivityRecordDao activityRecordDao;
    
    private ActivityRecord activityRecord1;
    private ActivityRecord activityRecord2;
    private ActivityRecord activityRecord3;
    
    private Activity activity1;
    private Activity activity2;
    
    private User user1;
    private User user2;
    
    private Calories cal1;
    private Calories cal2;
    
    private Calendar date1;
    private Calendar date2;
    
    @BeforeMethod
    public void createActivityRecords(){
        
        activityRecord1 = new ActivityRecord();
        activityRecord2 = new ActivityRecord();
        activityRecord3 = new ActivityRecord();
        
        date1 = Calendar.getInstance();
        date2 = Calendar.getInstance();
        
        user1 = new User();
        user2 = new User();
        
        activity1 = new Activity();
        activity2 = new Activity();       
        
        cal1 = new Calories();
        cal2 = new Calories();       
        
        user1.setName("Peter");
        user1.setEmail("peter@test.com");
        user1.setAge(20);
        user1.setSex(Sex.Male);
        user1.setWeight(90.5);
        
        user2.setName("Martina");
        user2.setEmail("martina@test.com");
        user2.setAge(40);
        user2.setSex(Sex.Female);
        user2.setWeight(70.5);
        
        activity1.setName("tennis");
        activity1.setMeasureDistance(Boolean.FALSE);
        activity1.setCalories(cal1);
        
        activity2.setName("cycling");
        activity2.setMeasureDistance(Boolean.TRUE);
        activity2.setCalories(cal2);
        
        date1.set(2015, 5, 23);
        date2.set(2015,8,4);
        
        activityRecord1.setActivity(activity1);
        activityRecord1.setDate(date1);
        activityRecord1.setDuration(120.0);
        activityRecord1.setUser(user1);
        
        activityRecord2.setActivity(activity2);
        activityRecord2.setDate(date2);
        activityRecord2.setDuration(35.5);
        activityRecord2.setUser(user2);
        activityRecord2.setDistance(55.3);
        
        activityRecord3.setActivity(activity1);
        activityRecord3.setDate(date1);
        activityRecord3.setDuration(67.7);
        activityRecord3.setUser(user2);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullDate() {
        activityRecord1.setDate(null);
        activityRecordDao.create(activityRecord1);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullUser() {
        activityRecord1.setUser(null);
        activityRecordDao.create(activityRecord1);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullActivity() {
        activityRecord1.setActivity(null);
        activityRecordDao.create(activityRecord1);
    }
    
    @Test
    public void deleteTest(){
        activityRecordDao.create(activityRecord1);
        activityRecordDao.create(activityRecord2);
        Assert.assertNotNull(activityRecordDao.getRecordById(activityRecord1.getId()));
        activityRecordDao.delete(activityRecord1);
        Assert.assertNull(activityRecordDao.getRecordById(activityRecord1.getId()));
    }
    
    @Test
    public void updateTest(){
        activityRecordDao.create(activityRecord1);
        activityRecord1.setDuration(222.11);
        activityRecordDao.update(activityRecord1);
        ActivityRecord ar = activityRecordDao.getRecordById(activityRecord1.getId());
        Assert.assertEquals(222.11, ar.getDuration());
        Assert.assertEquals(user1, ar.getUser());
        Assert.assertEquals(date1, ar.getDate());
    }   
       
    @Test
    public void getRecordByIdTest(){
        activityRecordDao.create(activityRecord1);
        ActivityRecord ar = activityRecordDao.getRecordById(activityRecord1.getId());
        Assert.assertNotNull(ar);
        Assert.assertEquals(activityRecord1.getId(), ar.getId());
        Assert.assertEquals(user1, ar.getUser());
        Assert.assertEquals(activity1, ar.getActivity());
        Assert.assertEquals(date1, ar.getDate());
        Assert.assertEquals(120.0, ar.getDuration());
    }

     
    @Test
    public void getRecordsByUserTest(){
        activityRecordDao.create(activityRecord1);
        activityRecordDao.create(activityRecord2);
        List<ActivityRecord> arList = activityRecordDao.getRecordsByUser(user2);
        Assert.assertTrue(arList.size() == 1);
        arList = activityRecordDao.getRecordsByUser(user1);
        Assert.assertTrue(arList.size() == 1);
        ActivityRecord ar = arList.get(0);
        Assert.assertEquals(user1, ar.getUser());       
    }

    @Test
    public void getRecordsByActivityTest(){
        activityRecordDao.create(activityRecord1);
        activityRecordDao.create(activityRecord2);
        List<ActivityRecord> arList = activityRecordDao.getRecordsByActivity(activity1);
        Assert.assertTrue(arList.size() == 1);
        arList = activityRecordDao.getRecordsByActivity(activity2);
        Assert.assertTrue(arList.size() == 1);
        ActivityRecord ar = arList.get(0);
        Assert.assertEquals(activity2, ar.getActivity()); 
    }
    
    
}