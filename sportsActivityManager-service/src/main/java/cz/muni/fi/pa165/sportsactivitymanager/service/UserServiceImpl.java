package cz.muni.fi.pa165.sportsactivitymanager.service;

import cz.muni.fi.pa165.sportsactivitymanager.Dao.UserDAO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;

import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

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
    public void createUser(User user) {
        userDao.create(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
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
    
}
