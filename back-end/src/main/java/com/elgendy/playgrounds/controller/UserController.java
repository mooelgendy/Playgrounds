package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.User;
import com.elgendy.playgrounds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController implements Serializable {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<User> getAll(){
        List<User> users = service.getAll();
        return users;
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Integer id){
        User user = service.getOne(id);
        return user;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody User user) {
        service.add(user);
    }

    @PutMapping("/")
    public void update(@Valid @RequestBody User user) {
        service.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
