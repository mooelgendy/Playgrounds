package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try{
            users = em.createQuery("From User", User.class).getResultList();
            Assert.notEmpty(users, "User's List is Empty");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        User userById = null;
        try{
            Assert.notNull(id, "User Id Must Not be Null!");
            userById = em.find(User.class, id);
            Assert.notNull(userById, "User Not Found!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return userById;
    }

    @Override
    public void save(User user) {
        try{
            Assert.notNull(user, "User You Want To Save Must Not be Null");
            em.persist(user);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try{
            Assert.notNull(user, "User You Want To update is Not Found!");
            em.merge(user);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try{
            Assert.notNull(user, "User You Want To Delete Must Not be Null");
            if(em.contains(user)){
                em.remove(user);
            }else {
                throw new RuntimeException("User You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
