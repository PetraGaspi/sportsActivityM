import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Dao.ActivityDAOImpl;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.PersistenceSampleApplicationContext;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michal Stefanik 422237
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityDAOTest extends TestCase {
    private Activity a1;
    private Activity a2;
    private Activity a3;
    private Activity a4;

    private ActivityDAO dao;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private EntityManager em;

    public void setUp() throws Exception {
        super.setUp();

        //activity1
        a1 = new Activity((long)1);
        a1.setMeasureDistance(false);
        a1.setName("Volley");

        Calories c1 = new Calories((long)1);
        c1.setIndex(10.8);
        a1.setCalories(c1);

        //activity3
        a3 = new Activity((long)3);
        a3.setMeasureDistance(true);
        a3.setName("Volley");

        Calories c3 = new Calories((long)3);
        c3.setIndex(8.10);
        a1.setCalories(c3);

        //activity4
        a4 = new Activity((long)4);
        a4.setMeasureDistance(false);
        a4.setName("Basket");

        Calories c4 = new Calories((long)4);
        c4.setIndex(8.10);
        a4.setCalories(c4);

        emf = Persistence.createEntityManagerFactory("ActivityManagerPersistence");
        em = emf.createEntityManager();

        dao = new ActivityDAOImpl(em);

        em.getTransaction().begin();
        em.persist(a1);
        em.persist(a3);
        em.persist(a4);
    }

    @Test
    public void testCreate() {
        a2 = new Activity((long)2);
        a2.setName("Hockey");

        Calories c1 = new Calories((long)1);
        c1.setIndex(10.8);
        a2.setCalories(c1);

        dao.create(a2);

        Assert.assertEquals(a2, dao.findById((long) 2));
    }

    @Test
    public void testUpdate() {
        Calories c1 = new Calories((long)1);
        c1.setIndex(9.7);
        a1.setCalories(c1);
        a1.setName("Beachvolley");

        dao.update(a1);

        Assert.assertEquals(a1, dao.findById(a1.getId()));
    }

    @Test
    public void testDelete() throws Exception {
        a2 = new Activity((long)2);
        a2.setName("Hockey");

        Calories c1 = new Calories((long)1);
        c1.setIndex(10.8);
        a2.setCalories(c1);

        dao.create(a2);

        Assert.assertEquals(a2, dao.findById((long) 2));

        dao.delete(a1);

        Assert.assertEquals(null, dao.findById((long) 2));
    }

    @Test
    public void testFindById() throws Exception {
        Assert.assertEquals(a1, dao.findById((long)1));

        Assert.assertEquals(null, dao.findById((long)3));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Activity> found = dao.findAll();
        assertEquals(3, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a1) || a.equals(a3) || a.equals(a4));
        }
    }
    @Test
    public void testFindDistance() throws Exception {
        List<Activity> found = dao.findAll();
        assertEquals(1, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a3));
        }
    }
    @Test
    public void testFindNonDistance() throws Exception {
        List<Activity> found = dao.findAll();
        assertEquals(3, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a1) ||a.equals(a4));
        }
    }
    @Test
    public void testFindByName() throws Exception {
        List<Activity> found = dao.findByName("Volley");
        assertEquals(2, found.size());
        for(Activity a: found){
            Assert.assertTrue(a.equals(a1) || a.equals(a3));
        }
    }
}