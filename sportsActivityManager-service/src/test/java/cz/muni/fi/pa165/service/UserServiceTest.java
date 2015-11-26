package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enum.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Petra Gasparikova
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Mock
    private UserDAO userDao;
    
    @Autowired
    @InjectMocks
    private UserService userService;
    
    private User user;
    
    @BeforeClass
    public void setup() throws ServiceException{
         MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setUpMethod() throws Exception {
        user = new User();
        user.setName("User Name");
        user.setAge(22);
        user.setEmail("test@java.fi");
        user.setSex(Sex.Male);
        user.setWeight(85.4);
    }
    
    @Test
    public void testGetUserByIdNotExisting() {
        assertNull(userService.getUserById(Long.MIN_VALUE));
    }

    private void assertDeepEquals(User user1, User user2) {
        assertEquals(user1, user2);
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getAge(), user2.getAge());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getWeight(), user2.getWeight());
        assertEquals(user1.getSex(), user2.getSex());
    }
}
