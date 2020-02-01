package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
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
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        User userById = null;
        try{
            userById = em.find(User.class, id);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return userById;
    }

    @Override
    public void save(User user) {
        try{
            em.persist(user);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try{
            em.merge(user);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try{
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
