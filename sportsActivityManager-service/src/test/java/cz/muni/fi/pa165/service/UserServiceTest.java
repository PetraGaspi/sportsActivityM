package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enum.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.hibernate.service.spi.ServiceException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        user.setHeight(170.5);
    }  
    
    @Test
    public void testCreateUser() {
        userService.createUser(user);
        verify(userDao).create(user);
    }
       
    @Test
    public void testUpdateUser() {
        user.setName("New User");
        user.setEmail("user@pa165.fi");
        user.setWeight(91.74);
        userService.updateUser(user);
        verify(userDao).update(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(user);
        verify(userDao).delete(user);
    }

    @Test
    public void testGetUserByIdNotExisting() {      
        when(userDao.findById(Long.MIN_VALUE)).thenReturn(null);
        assertNull(userService.getUserById(Long.MIN_VALUE));
    }
    
    @Test
    public void testGetUserById() {
        user.setId(1l); 
        when(userDao.findById(user.getId())).thenReturn(user);
        assertDeepEquals(user, userService.getUserById(user.getId()));
    }
    
    @Test
    public void testGetUserByEmail() {
        when(userDao.findByEmail(user.getEmail())).thenReturn(user);
        assertDeepEquals(user, userService.getUserByEmail(user.getEmail()));
    }
    
    @Test
    public void testgetUsersByName(){
        User u = new User();
        u.setName(user.getName());
        u.setEmail("charlie@man.man");
        u.setSex(Sex.Male);
        u.setAge(55);
        u.setWeight(102.0);
        u.setHeight(198.0);
        when(userDao.findByName(user.getName())).thenReturn(Arrays.asList(user,u));
    }
    
    @Test
    public void testGetAllUsers() {
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));
        assertEquals(userService.getAllUsers().size(), 1);
        User u = new User();
        u.setName("Charlie Man");
        u.setEmail("charlie@man.man");
        u.setSex(Sex.Male);
        u.setAge(55);
        u.setWeight(102.0);
        u.setHeight(198.0);
        when(userDao.findAll()).thenReturn(Arrays.asList(user,u));
        assertEquals(userService.getAllUsers().size(), 2);
        userDao.delete(user);
        when(userDao.findAll()).thenReturn(Collections.singletonList(user));
        assertEquals(userService.getAllUsers().size(), 1);
        userDao.delete(u);
        when(userDao.findAll()).thenReturn(new ArrayList<>());
        assertEquals(userService.getAllUsers().size(), 0);          
    }
    
    @Test
    public void testCalculateBMI(User user){        
        
        user.setWeight(100.0);
        user.setHeight(200.0);
        userDao.update(user);
        double bmi = user.getWeight()/(user.getHeight()/100)*(user.getHeight()/100);
        assertEquals(userService.calculateBMI(user),bmi,0.0);
        
        user.setHeight(0.0);
        userDao.update(user);
        assertEquals(userService.calculateBMI(user),0.0,0.0);       
    }
    
    private void assertDeepEquals(User user1, User user2) {
        assertEquals(user1, user2);
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getAge(), user2.getAge());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getWeight(), user2.getWeight());
        assertEquals(user1.getHeight(), user2.getHeight());
        assertEquals(user1.getSex(), user2.getSex());
    }
}
