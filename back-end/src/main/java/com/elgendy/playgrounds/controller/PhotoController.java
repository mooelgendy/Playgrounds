package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.exception.ApiNotFoundException;
import com.elgendy.playgrounds.exception.InternalServerErrorException;
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
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/photo")
public class PhotoController implements Serializable {

    private PhotoService service;
    private static Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);
    private String exceptionMessage = "Error Occurred!";

    @Autowired
    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<PhotoDTO> getAll(){
        List<Photo> photos = null;
        List<PhotoDTO> photoDTOs = null;
        try{
            photos = service.getAll();
            photoDTOs = photos.stream().map(photo -> {
                PhotoDTO dto = new PhotoDTO();
                dto.setId(photo.getId());
                dto.setName(photo.getName());
                dto.setLink(photo.getLink());
                return dto;
            }).collect(Collectors.toList());
            return photoDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
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
            if (e instanceof NullPointerException){
                throw new ApiNotFoundException("Photo with Id: "+ id + " is not found");
            } else {
                throw new InternalServerErrorException(exceptionMessage);
            }
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
            throw new InternalServerErrorException(exceptionMessage);
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
