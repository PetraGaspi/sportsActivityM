import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.PersistenceSampleApplicationContext;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityDAOTest extends TestCase {
    private Activity a;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private EntityManager em;

    public void setUp() throws Exception {
        super.setUp();

        a = new Activity((long)1);
        a.setMeasureDistance(true);
        a.setName("Volley");

        emf = Persistence.createEntityManagerFactory("ActivityManagerPersistence");
        em = emf.createEntityManager();

        em.per
    }

    @Test
    public void testCreate() throws Exception {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testUpdate() throws Exception {

    }
    @Test
    public void testDelete() throws Exception {

    }
    @Test
    public void testFindById() throws Exception {

    }
    @Test
    public void testFindAll() throws Exception {

    }
    @Test
    public void testFindDistance() throws Exception {

    }
    @Test
    public void testFindNonDistance() throws Exception {

    }
    @Test
    public void testFindByName() throws Exception {

    }
}