package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll() throws RuntimeException;

    User getOne(Integer id);

    void add(User user);

    void update(User user);

    void delete(Integer id);
}
