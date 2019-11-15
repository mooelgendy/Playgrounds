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
        Assert.notNull(service, "service must not be null!");
        this.service = service;
    }

    @GetMapping("/")
    public List<Photo> getAll(){
        try {
            List<Photo> photos = service.getAll();
            return photos;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Photo findOne(@PathVariable("id") Integer id){
        try{
            Photo photo1 = service.getOne(id);
            return photo1;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @PostMapping("/")
    public String create(@Valid @RequestBody Photo photo) {
        try {
            if (photo == null) {
                return "photo You Want To Save Must not Be Null!";
            } else if (photo == service.getOne(photo.getId())) {
                return "photo Is Already Exist";
            }
            service.add(photo);
            return "photo created successfully with id: " + photo.getId().toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in creating new photo: " + ex.toString();
        }

    }

    @PutMapping("/")
    public String update(@Valid @RequestBody Photo photo) {
        try {
            if (service.getOne(photo.getId()) == null) {
                return "photo with id:" + photo.getId().toString() + " is Not Found!";
            }
            service.update(photo);
            return "photo with id:" + photo.getId().toString() + " is updated successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in updating photo: " + ex.toString();
        }
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            if (service.getOne(id) == null) {
                return "photo with id:" + id.toString() + " is Not Found!";
            }
            service.delete(id);
            return "photo with id:" + id.toString() + " is deleted successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in deleting photo: " + ex.toString();
        }
    }
}
