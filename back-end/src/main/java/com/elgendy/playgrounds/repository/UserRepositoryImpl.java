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
        Assert.notNull(entityManager, "EntityManager must not be null!");
        this.em = entityManager;
    }

    @Override
    public List<User> findAll() {
        List<User> users = em.createQuery("From User", User.class).getResultList();
        Assert.notEmpty(users, "List is Empty");
        return users;
    }

    @Override
    public User findById(Integer id) {
        Assert.notNull(id, "User Id Must Not be Null!");
        User userById = em.find(User.class, id);
        Assert.notNull(userById, "User Not Found!");
        return userById;
    }

    @Override
    public void save(User user) {
        Assert.notNull(user, "User You Want To Save Must Not be Null");
        try{
            em.persist(user);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "User You Want To update is Not Found!");
        try{
            em.merge(user);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(User user) {
        Assert.notNull(user, "User You Want To Delete Must Not be Null");
        try{
            if(em.contains(user)){
                em.remove(user);
            }else {
                throw new RuntimeException("User You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}
