package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.UserDTO;
import com.elgendy.playgrounds.model.User;
import com.elgendy.playgrounds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
    public List<UserDTO> getAll(){
        List<User> users = service.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            UserDTO dto = new UserDTO();
            dto.setId(it.next().getId());
            dto.setName(it.next().getName());
            dto.setAddress(it.next().getAddress());
            dto.setBio(it.next().getBio());
            dto.setChosenTime(it.next().getChosenTime());
            dto.setPhone(it.next().getPhone());
            dto.setPosition(it.next().getPosition());
            dto.setProfilePhoto(it.next().getProfilePhoto());
            dto.setCoverPhoto(it.next().getCoverPhoto());
            userDTOs.add(dto);
        }
        return userDTOs;
    }

    @GetMapping("/{id}")
    public UserDTO findOne(@PathVariable("id") Integer id){
        User user = service.getOne(id);
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAddress(user.getAddress());
        dto.setBio(user.getBio());
        dto.setChosenTime(user.getChosenTime());
        dto.setPhone(user.getPhone());
        dto.setPosition(user.getPosition());
        dto.setProfilePhoto(user.getProfilePhoto());
        dto.setCoverPhoto(user.getCoverPhoto());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setBio(dto.getBio());
        user.setChosenTime(dto.getChosenTime());
        user.setPhone(dto.getPhone());
        user.setPosition(dto.getPosition());
        user.setProfilePhoto(dto.getProfilePhoto());
        user.setCoverPhoto(dto.getCoverPhoto());
        service.add(user);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setBio(dto.getBio());
        user.setChosenTime(dto.getChosenTime());
        user.setPhone(dto.getPhone());
        user.setPosition(dto.getPosition());
        user.setProfilePhoto(dto.getProfilePhoto());
        user.setCoverPhoto(dto.getCoverPhoto());
        service.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
