package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.Store;
import com.elgendy.playgrounds.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private StoreService service;

    @Autowired
    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Store> getAll(){
        List<Store> items = service.getAll();
        return items;
    }

    @GetMapping("/{id}")
    public Store findOne(@PathVariable("id") Integer id){
        Store item = service.getOne(id);
        return item;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody Store item) {
        service.add(item);
    }

    @PutMapping("/")
    public void update(@Valid @RequestBody Store item) {
        service.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}