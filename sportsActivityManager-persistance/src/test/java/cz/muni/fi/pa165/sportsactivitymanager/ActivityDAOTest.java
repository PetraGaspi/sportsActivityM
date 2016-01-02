package cz.muni.fi.pa165.sportsactivitymanager;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Stefanik 422237
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityDAOTest extends AbstractTestNGSpringContextTests {
    private Activity a1;
    private Activity a2;
    private Activity a3;
    private Activity a4;

    @Autowired
    private ActivityDAO activityDao;

    @Autowired
    private CaloriesDAO caloriesDao;

    @PersistenceContext
    private EntityManager em;

    /**
     * setUp creates and persists 3 Activity objects that the tests run on
     */
    @BeforeMethod
    public void setUp() {

        //activity1
        a1 = new Activity();
        a1.setMeasureDistance(false);
        a1.setName("Volley");

        Calories c1 = new Calories();
        c1.setIndex(10.8);
        a1.setCalories(c1);

        //activity3
        a3 = new Activity();
        a3.setMeasureDistance(true);
        a3.setName("Volley");

        Calories c3 = new Calories();
        c3.setIndex(8.10);
        a3.setCalories(c3);

        //activity4
        a4 = new Activity();
        a4.setMeasureDistance(false);
        a4.setName("Basket");

        Calories c4 = new Calories();
        c4.setIndex(8.10);
        a4.setCalories(c4);

        em.persist(c1);
        em.persist(c3);
        em.persist(c4);

        em.persist(a1);
        em.persist(a3);
        em.persist(a4);


    }

    @AfterMethod
    public void destroy() {
    }

    @Test
    public void testFindById() {
        Assert.assertEquals(a1, activityDao.findById(a1.getId()));

        Assert.assertEquals(null, activityDao.findById((long) -1));
    }

    @Test
    public void testFindAll() {
        List<Activity> found = activityDao.findAll();

        Assert.assertEquals(3, found.size());
        for (Activity a : found) {
            Assert.assertTrue(a.equals(a1) || a.equals(a3) || a.equals(a4));
        }
    }

    @Test
    public void testFindDistance() {
        List<Activity> found = activityDao.findDistance();
        Assert.assertEquals(1, found.size());
        for (Activity a : found) {
            Assert.assertTrue(a.equals(a3));
        }
    }

    @Test
    public void testFindNonDistance() {
        List<Activity> found = activityDao.findNonDistance();
        Assert.assertEquals(2, found.size());
        for (Activity a : found) {
            Assert.assertTrue(a.equals(a1) || a.equals(a4));
        }
    }

    @Test
    public void testFindByName() {
        List<Activity> found = activityDao.findByName("Volley");
        Assert.assertEquals(2, found.size());
        for (Activity a : found) {
            Assert.assertTrue(a.equals(a1) || a.equals(a3));
        }
    }

    @Test
    public void testCreate() {
        a2 = new Activity();
        a2.setName("Hockey");
        a2.setMeasureDistance(false);
        Calories c1 = new Calories();
        c1.setIndex(10.8);
        caloriesDao.create(c1);

        a2.setCalories(c1);

        Assert.assertEquals(0, activityDao.findByName(a2.getName()).size());

        activityDao.create(a2);

        Assert.assertEquals(a2, activityDao.findById(a2.getId()));

    }

    @Test
    public void testUpdate() {
        Calories c1 = new Calories();
        c1.setIndex(9.7);

        //assure that updated object exists
        Assert.assertNotNull(em.find(Activity.class, a1.getId()));
        a1.setCalories(c1);
        a1.setName("Beachvolley");

        activityDao.update(a1);

        Assert.assertEquals(9.7, activityDao.findById(a1.getId()).getCalories().getIndex());
    }

    @Test
    public void testDelete() {

        //assure that the a4 is persisted
        Assert.assertEquals(a4, activityDao.findByName("Basket").get(0));

        activityDao.delete(a4);

        //assure that a4 is no longer persisted
        Assert.assertEquals(new ArrayList<Activity>(), activityDao.findByName("Basket"));
    }
}