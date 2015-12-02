package org.currconv.dao.hbn;

import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.currconv.entities.user.User;
import org.currconv.dao.AbstractDao;
import org.currconv.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository ("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        return getByKey(id);
    }
    
    public User findByUserName(String username) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username", username));
        return (User) criteria.uniqueResult();
    }

    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUser(User user){
        delete(user);
    }

    public List<User> findAllUsers(){
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    public boolean canLogin(User user){
        return true;
    }
 
}