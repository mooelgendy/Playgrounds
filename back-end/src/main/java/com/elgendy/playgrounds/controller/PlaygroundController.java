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
        this.service = service;
    }

    @GetMapping("/")
    public List<Playground> getAll(){
        List<Playground> playgrounds = service.getAll();
        return playgrounds;
    }

    @GetMapping("/{id}")
    public Playground findOne(@PathVariable("id") Integer id){
        Playground playground = service.getOne(id);
        return playground;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody Playground playground) {
        service.add(playground);
    }

    @PutMapping("/")
    public void update(@Valid @RequestBody Playground playground) {
        service.update(playground);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
