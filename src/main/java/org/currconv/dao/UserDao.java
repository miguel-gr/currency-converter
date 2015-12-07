package org.currconv.dao;

import java.util.List;
 
import org.currconv.entities.user.User;
 
public interface UserDao {

    User findById(int id);

    User findByUserName(String userName);

    void saveUser(User user);

    void deleteUser(User user);

    List<User> findAllUsers();
 
}