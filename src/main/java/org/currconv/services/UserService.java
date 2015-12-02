package org.currconv.services;

import java.util.List;
import org.currconv.entities.user.User;

public interface UserService {
 
    void saveUser(User user);
 
    List<User> findAllUsers();
 
    User findByUserName(String name);
 
}