package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.Photo;
import com.elgendy.playgrounds.service.PhotoService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/photos")
public class PhotoController implements Serializable {

    private PhotoService service;

    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Photo> getAll(){
        List<Photo> photos = service.getAll();
        return photos;
    }

    @GetMapping("/{id}")
    public Photo findOne(@PathVariable("id") Integer id){
        Photo photo = service.getOne(id);
        return photo;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody Photo photo) {
        service.add(photo);
    }

    @PutMapping("/")
    public void update(@Valid @RequestBody Photo photo) {
        service.update(photo);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
