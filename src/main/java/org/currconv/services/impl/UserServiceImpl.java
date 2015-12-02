package org.currconv.services.impl;

import java.util.List;
import org.currconv.dao.UserDao;
import org.currconv.entities.user.User;
import org.currconv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDao dao;
 
    public void saveUser(User user) {
        dao.saveUser(user);
    }
 
    public List<User> findAllUsers(){
        return dao.findAllUsers();
    }
 
    public User findByUserName(String name){
        return dao.findByUserName(name);
    }
 
}