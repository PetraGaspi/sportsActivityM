package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enum.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.service.config.ServiceConfiguration;

import org.hibernate.service.spi.ServiceException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    @InjectMocks
    private UserService userService;
    
    @BeforeClass
    public void setup() throws ServiceException{
         MockitoAnnotations.initMocks(this);
    }
    
    private User user;
    
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
    public void testCreateUser() {
        User u = userService.createUser(user);
        assertDeepEquals(userDao.findById(u.getId()), user);
    }
       
    @Test
    public void testUpdateUser() {
        userDao.create(user);
        user.setName("New User");
        user.setEmail("user@pa165.fi");
        user.setWeight(91.74);
        userService.updateUser(user);
        assertDeepEquals(user, userDao.findById(user.getId()));
    }

    @Test
    public void testDeleteUser() {
        userDao.create(user);
        assertNotNull(userDao.findById(user.getId()));
        userService.deleteUser(user);
        assertNull(userDao.findById(user.getId()));
    }

    @Test
    public void testGetUserByIdNotExisting() {       
        assertNull(userService.getUserById(Long.MIN_VALUE));
    }
    
    @Test
    public void testGetUserById() {
        userDao.create(user);                  
        assertDeepEquals(user, userService.getUserById(user.getId()));
    }
    
    @Test
    public void testGetUserByEmail() {
        userDao.create(user);
        assertDeepEquals(user, userService.getUserByEmail(user.getEmail()));
    }
    
    @Test
    public void testGetAllUsers() {
        assertEquals(userService.getAllUsers().size(), 0);
        User u = new User();
        u.setName("Charlie Man");
        u.setEmail("charlie@man.man");
        u.setSex(Sex.Male);
        u.setAge(55);
        u.setWeight(102.0);
        userDao.create(u);
        assertEquals(userService.getAllUsers().size(), 1);
        userDao.create(user);
        assertEquals(userService.getAllUsers().size(), 2);
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
