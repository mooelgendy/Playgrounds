package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.PhotoDTO;
import com.elgendy.playgrounds.model.Photo;
import com.elgendy.playgrounds.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
    public List<PhotoDTO> getAll(){
        List<Photo> photos = service.getAll();
        List<PhotoDTO> photoDTOs = new ArrayList<>();
        Iterator<Photo> it = photos.iterator();
        while(it.hasNext()){
            PhotoDTO dto = new PhotoDTO();
            dto.setId(it.next().getId());
            dto.setName(it.next().getName());
            dto.setLink(it.next().getLink());
            photoDTOs.add(dto);
        }
        return photoDTOs;
    }

    @GetMapping("/{id}")
    public PhotoDTO findOne(@PathVariable("id") Integer id){
        Photo photo = service.getOne(id);
        PhotoDTO dto = new PhotoDTO();
        dto.setId(photo.getId());
        dto.setLink(photo.getLink());
        dto.setName(photo.getName());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PhotoDTO dto) {
        Photo photo = new Photo();
        photo.setName(dto.getName());
        photo.setLink(dto.getLink());
        service.add(photo);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody PhotoDTO dto) {
        Photo photo = new Photo();
        photo.setName(dto.getName());
        photo.setLink(dto.getLink());
        service.update(photo);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
