import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAOImpl;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAOImpl;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.PersistenceSampleApplicationContext;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michal Stefanik 422237
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityDAOTest extends AbstractTestNGSpringContextTests {
    private static Activity a1;
    private static Activity a2;
    private static Activity a3;
    private static Activity a4;

    private static boolean setup = false;

    @Autowired
    private static ActivityDAO activityDao;

    @Autowired
    private static CaloriesDAO caloriesDao;

    @PersistenceUnit
    private static EntityManagerFactory emf;

    private static EntityManager em;

    /**
     * setUp creates and persists 3 Activity objects that the tests run on
     */
    @BeforeMethod
    public static void setUp() {
        if(!setup){
            System.out.println("setup started");

            emf = Persistence.createEntityManagerFactory("ActivityManagerPersistence");
            em = emf.createEntityManager();


            caloriesDao = new CaloriesDAOImpl(em);
            activityDao = new ActivityDAOImpl(em);
            //TODO stale nevieme activityDao injectovat pomocou Springu

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

            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c3);
            em.persist(c4);

            em.persist(a1);
            em.persist(a3);
            em.persist(a4);

            setup = true;

            System.out.println("setup done");
        }
    }

    @AfterMethod
    public void destroy(){

        em.clear();
        emf.close();
    }

    @Test
    public void testFindById() {
        setUp();
        //TODO preco sa @BeforeTest nespusta automaticky?
        //TODO ked bude @BeforeTest robit co ma, odstranit bool setup

        Assert.assertTrue(true);

        Assert.assertEquals(a1, activityDao.findById((long)1));

        Assert.assertEquals(null, activityDao.findById((long)5));

        Assert.assertTrue(true);
    }

    @Test
    public void testFindAll() {
        setUp();

        List<Activity> found = activityDao.findAll();
        Assert.assertEquals(3, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a1) || a.equals(a3) || a.equals(a4));
        }
    }
    @Test
    public void testFindDistance() {
        setUp();

        List<Activity> found = activityDao.findDistance();
        Assert.assertEquals(1, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a3));
        }
    }
    @Test
    public void testFindNonDistance() {
        setUp();

        List<Activity> found = activityDao.findNonDistance();
        Assert.assertEquals(3, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a1) ||a.equals(a4));
        }
    }
    @Test
    public void testFindByName() {
        setUp();

        List<Activity> found = activityDao.findByName("Volley");
        Assert.assertEquals(2, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a1) || a.equals(a3));
        }
    }

    @Test
    public void testCreate() {
        setUp();

        a2 = new Activity();
        a2.setName("Hockey");

        Calories c1 = new Calories();
        c1.setIndex(10.8);
        caloriesDao.create(c1);

        a2.setCalories(c1);

        activityDao.create(a2);

        Assert.assertEquals(a2, activityDao.findById((long) 4));
        Assert.assertEquals(10.8, activityDao.findById((long) 4).getCalories().getIndex());

    }

    @Test
    public void testUpdate() {
        setUp();

        Calories c1 = new Calories((long)3);
        c1.setIndex(9.7);
        a2 = em.find(Activity.class, (long)1);
        a2.setCalories(c1);
        a2.setName("Beachvolley");

        activityDao.update(a2);

        Assert.assertEquals(9.7, activityDao.findById((long)1).getCalories().getIndex());
    }

    @Test
    public void testDelete() {
        setUp();

        a2 = em.find(Activity.class, (long)2);

        //assure that the a2 is persisted
        Assert.assertEquals(a2, activityDao.findById((long) 2));

        activityDao.delete(a2);

        //assure that a2 is no longer persisted
        Assert.assertEquals(null, activityDao.findById((long) 2));
    }
}