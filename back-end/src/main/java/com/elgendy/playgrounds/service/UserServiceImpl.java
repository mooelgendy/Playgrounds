package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.User;
import com.elgendy.playgrounds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        Assert.notNull(repository, "repository must not be null!");
        this.repository = repository;
    }

    @Override
    public List<User> getAll() throws RuntimeException{
        try {
            List<User> userList = repository.findAll();
            return userList;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public User getOne(Integer id) {
        Assert.notNull(id, "User Id Must Not be Null!");
        try {
            User userById = repository.findById(id);
            return userById;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public void add(User user) {
        Assert.notNull(user, "User You Want To Save Must Not be Null!");
        try {
            repository.save(user);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "User You Want To Update Must Not be Null!");
        try {
            repository.update(user);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "User Id Must Not be Null!");
        try {
            User deletedUser = repository.findById(id);
            Assert.notNull(deletedUser, "User You Want To Delete is Not Found!");
            repository.delete(deletedUser);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
