package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Petra Gasparikova
 */

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDAO userDao;

    @Override
    public User createUser(User user) {
        userDao.create(user);
        return(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
       return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
    
    @Override
    public List<User> getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Double calculateBMI(User user) {
        double height = user.getHeight();
        if(height == 0.0)
            return 0.0;
        height = height/100;
        double bmi = (user.getWeight())/(height*height);
        bmi = Math.round(bmi * 100.0) / 100.0;
        return bmi;
    }

}
