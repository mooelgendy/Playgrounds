package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.exception.ApiNotFoundException;
import com.elgendy.playgrounds.exception.InternalServerErrorException;
import com.elgendy.playgrounds.model.DTO.UserDTO;
import com.elgendy.playgrounds.model.User;
import com.elgendy.playgrounds.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController implements Serializable {

    private UserService service;
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private String exceptionMessage = "Error Occurred!";

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<UserDTO> getAll(){
        List<User> users = null;
        List<UserDTO> userDTOs = null;
        try {
            users = service.getAll();
            userDTOs = users.stream().map(user -> {
                UserDTO dto = new UserDTO();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setAddress(user.getAddress());
                dto.setPosition(user.getPosition());
                dto.setPhone(user.getPhone());
                dto.setBio(user.getBio());
                return dto;
            }).collect(Collectors.toList());
            return userDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }
    }

    @GetMapping("/{id}")
    public UserDTO findOne(@PathVariable("id") Integer id){
        User user = null;
        UserDTO dto = null;
        try{
            user = service.getOne(id);
            dto = new UserDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setAddress(user.getAddress());
            dto.setBio(user.getBio());
            dto.setPhone(user.getPhone());
            dto.setPosition(user.getPosition());
            return dto;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            if (e instanceof NullPointerException){
                throw new ApiNotFoundException("User with Id: "+ id + " is not found");
            } else {
                throw new InternalServerErrorException(exceptionMessage);
            }
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserDTO dto) {
        User user = null;
        try{
            user = new User();
            user.setName(dto.getName());
            user.setAddress(dto.getAddress());
            user.setBio(dto.getBio());
            user.setPhone(dto.getPhone());
            user.setPosition(dto.getPosition());
            service.add(user);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody UserDTO dto) {
        User user = null;
        try{
            user = new User();
            user.setId(dto.getId());
            user.setName(dto.getName());
            user.setAddress(dto.getAddress());
            user.setBio(dto.getBio());
            user.setPhone(dto.getPhone());
            user.setPosition(dto.getPosition());
            service.update(user);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        try{
            service.delete(id);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }

    }
}
