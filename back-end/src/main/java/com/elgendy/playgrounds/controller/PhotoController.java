package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.PhotoDTO;
import com.elgendy.playgrounds.model.Photo;
import com.elgendy.playgrounds.service.PhotoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/photo")
public class PhotoController implements Serializable {

    private PhotoService service;
    private static Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<PhotoDTO> getAll(){
        List<Photo> photos = null;
        List<PhotoDTO> photoDTOs = null;
        Iterator<Photo> it = null;
        try{
            photos = service.getAll();
            photoDTOs = new ArrayList<>();
            it = photos.iterator();
            while(it.hasNext()){
                Photo photo = it.next();
                PhotoDTO dto = new PhotoDTO();
                dto.setId(photo.getId());
                dto.setName(photo.getName());
                dto.setLink(photo.getLink());
                photoDTOs.add(dto);
            }
            return photoDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @GetMapping("/{id}")
    public PhotoDTO findOne(@PathVariable("id") Integer id){
        Photo photo = null;
        PhotoDTO dto = null;
        try{
            photo = service.getOne(id);
            dto = new PhotoDTO();
            dto.setId(photo.getId());
            dto.setLink(photo.getLink());
            dto.setName(photo.getName());
            return dto;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PhotoDTO dto) {
        Photo photo = null;
        try{
            photo = new Photo();
            photo.setName(dto.getName());
            photo.setLink(dto.getLink());
            service.add(photo);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody PhotoDTO dto) {
        Photo photo = null;
        try{
            photo = new Photo();
            photo.setId(dto.getId());
            photo.setName(dto.getName());
            photo.setLink(dto.getLink());
            service.update(photo);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        try{
            service.delete(id);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }
}
