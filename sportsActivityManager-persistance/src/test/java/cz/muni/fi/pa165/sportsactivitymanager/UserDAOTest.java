package cz.muni.fi.pa165.sportsactivitymanager;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.Sex;

import java.util.List;
import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *  Test class for UserDao functionality
 *  @author Juraj Pleško, 359530
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@org.springframework.transaction.annotation.Transactional
public class UserDAOTest extends AbstractTestNGSpringContextTests{
    
    @javax.persistence.PersistenceContext
    public EntityManager em;
    
    @Autowired
    public UserDAO userDao;
            
    private User user1;
    private User user2;
    private User user3;

    
    /**
    *   
    *   Setup for testing
    */
    @BeforeMethod        
    public void createUsers(){
        user1 = new User();
        user1.setName("Jozef");
        user1.setAge(25);
        user1.setSex(Sex.Male);
        user1.setWeight(99.99);
        user1.setHeight(182.5);
        user1.setEmail("test@test.cz");
        
        
        user2 = new User();
        user2.setName("Lenka");
        user2.setAge(28);
        user2.setSex(Sex.Female);
        user2.setWeight(66.66);
        user2.setHeight(165.1);
        user2.setEmail("test2@test2.cz");
        
        
        user3 = new User();        
        user3.setName("Ferdo");
        user3.setAge(22);
        user3.setSex(Sex.Male);
        user3.setWeight(33.33);
        user3.setHeight(100.0);
        user3.setEmail("test3@test3.cz");
       
        
        
    }
    /**
    *   testing findByName call for both positive and negative reults.
    *   
    */
    @Test
    public void findByName(){
        userDao.create(user1);
        List<User> users;
        users = userDao.findByName("Jozef");
        Assert.assertEquals(users,userDao.findByName("Jozef"));
        Assert.assertNotSame(users, userDao.findByName("Ferdo"));
        Assert.assertNotSame(userDao.findByName("Lenka"), userDao.findByName("Ferdo"));
    }
    /**
    *   testing deletion of persisted users
    *   
    */
    @Test
    public void delete(){
        user1 = new User();
        user1.setName("Jozef");
        user1.setAge(25);
        user1.setSex(Sex.Male);
        user1.setWeight(99.99);
        user1.setHeight(182.5);
        user1.setEmail("test@test.cz");
        userDao.create(user1);
        Assert.assertNotNull(userDao.findById(user1.getId()));
        userDao.delete(user1);
        Assert.assertNull(userDao.findById(user1.getId()));
    }
    /**
    *   testing findById 
    *   
    */
    @Test
    public void findByID(){
        userDao.create(user1);
        Assert.assertEquals(user1, userDao.findById(user1.getId()));
    }
    /**
    *   testing findAll 
    *   
    */
    @Test
    public void findAll(){
        userDao.create(user1);
        userDao.create(user2);
        List<User> all = userDao.findAll();
        Assert.assertEquals(2, all.size());
        for(User u : all){
            Assert.assertTrue(u.equals(user1) || u.equals(user2));
        }
    }    
    /**
    *   testing findByEmail
    *   
    */
    @Test
    public void findByEmail(){
        userDao.create(user1);
        Assert.assertEquals(user1, userDao.findByEmail("test@test.cz"));
    }
    
    
}
