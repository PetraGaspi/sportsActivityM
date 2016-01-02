package cz.muni.fi.pa165.sportsactivitymanager;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.CaloriesDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import junit.framework.Assert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Michal Stefanik 422237
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CaloriesDAOTest extends AbstractTestNGSpringContextTests {

    private Calories c1;
    private Calories c2;
    private Calories c3;
    private Calories c4;

    @Inject
    private CaloriesDAO dao;

    @PersistenceContext
    private EntityManager em;

    @BeforeMethod
    public void setUp() throws Exception {

        c1 = new Calories();
        c2 = new Calories();

        c1.setIndex(8.5);
        c2.setIndex(7.4);

        em.persist(c1);
        em.persist(c2);
    }

    @Test
    public void testCreate() throws Exception {
        c4 = new Calories();

        c4.setIndex(5.2);

        dao.create(c4);

        Assert.assertEquals(c4, em.find(Calories.class, c4.getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        Assert.assertEquals(c2.getIndex(), dao.findById(c2.getId()).getIndex());

        c2.setIndex(9.6);

        dao.update(c2);

        Assert.assertEquals(9.6, dao.findById(c2.getId()).getIndex());
    }

    @Test
    public void testDelete() throws Exception {
        c3 = new Calories();

        c3.setIndex(6.3);

        em.persist(c3);

        Assert.assertFalse(dao.findById(c3.getId()) == null);
        dao.delete(c3);
        Assert.assertTrue(dao.findById(c3.getId()) == null);
    }

    @Test
    public void testFindById() throws Exception {
        Assert.assertEquals(c1, dao.findById(c1.getId()));
        Assert.assertEquals(null, dao.findById((long) -1));
    }

    @Test
    public void testFindAll() throws Exception {
        Assert.assertEquals(2, dao.findAll().size());
        for (Calories c : dao.findAll()) {
            Assert.assertTrue(c.equals(c1) || c.equals(c2));
        }

    }
}