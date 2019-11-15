package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.Playground;
import com.elgendy.playgrounds.service.PlaygroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/playground")
public class PlaygroundController implements Serializable {

    private PlaygroundService service;

    @Autowired
    public PlaygroundController(PlaygroundService service) {
        Assert.notNull(service, "service must not be null!");
        this.service = service;
    }

    @GetMapping("/")
    public List<Playground> getAll(){
        try {
            List<Playground> playgrounds = service.getAll();
            return playgrounds;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Playground findOne(@PathVariable("id") Integer id){
        try{
            Playground playground1 = service.getOne(id);
            return playground1;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @PostMapping("/")
    public String create(@Valid @RequestBody Playground playground) {
        try {
            if (playground == null) {
                return "playground You Want To Save Must not Be Null!";
            } else if (playground == service.getOne(playground.getId())) {
                return "playground Is Already Exist";
            }
            service.add(playground);
            return "playground created successfully with id: " + playground.getId().toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in creating new playground: " + ex.toString();
        }

    }

    @PutMapping("/")
    public String update(@Valid @RequestBody Playground playground) {
        try {
            if (service.getOne(playground.getId()) == null) {
                return "playground with id:" + playground.getId().toString() + " is Not Found!";
            }
            service.update(playground);
            return "playground with id:" + playground.getId().toString() + " is updated successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in updating playground: " + ex.toString();
        }
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            if (service.getOne(id) == null) {
                return "playground with id:" + id.toString() + " is Not Found!";
            }
            service.delete(id);
            return "playground with id:" + id.toString() + " is deleted successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in deleting playground: " + ex.toString();
        }
    }
}
