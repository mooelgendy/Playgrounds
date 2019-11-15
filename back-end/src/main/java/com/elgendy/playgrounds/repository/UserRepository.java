package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.User;

import java.util.List;


public interface UserRepository{

    void save(User user);

    void update(User user);

    User findById(Integer id);

    List<User> findAll();

    void delete(User user);
}
