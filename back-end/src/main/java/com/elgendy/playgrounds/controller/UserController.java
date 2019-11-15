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
        Assert.notNull(service, "service must not be null!");
        this.service = service;
    }

    @GetMapping("/")
    public List<User> getAll(){
        try {
            List<User> users = service.getAll();
            return users;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return new ArrayList<>();
        }
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Integer id){
        try{
            User user1 = service.getOne(id);
            return user1;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @PostMapping("/")
    public String create(@Valid @RequestBody User user) {
        try {
            if (user == null) {
                return "User You Want To Save Must not Be Null!";
            } else if (user == service.getOne(user.getId())) {
                return "User Is Already Exist";
            }
            service.add(user);
            return "User created successfully with id: " + user.getId().toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in creating new user: " + ex.toString();
        }

    }

    @PutMapping("/")
    public String update(@Valid @RequestBody User user) {
        try {
            if (service.getOne(user.getId()) == null) {
                return "User with id:" + user.getId().toString() + " is Not Found!";
            }
            service.update(user);
            return "User with id:" + user.getId().toString() + " is updated successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in updating user: " + ex.toString();
        }
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            if (service.getOne(id) == null) {
                return "User with id:" + id.toString() + " is Not Found!";
            }
            service.delete(id);
            return "User with id:" + id.toString() + " is deleted successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in deleting user: " + ex.toString();
        }
    }
}
