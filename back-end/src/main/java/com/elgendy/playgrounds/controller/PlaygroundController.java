package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.PlaygroundDTO;
import com.elgendy.playgrounds.model.Playground;
import com.elgendy.playgrounds.service.PlaygroundService;
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
@RequestMapping("/api/playground")
public class PlaygroundController implements Serializable {

    private PlaygroundService service;

    @Autowired
    public PlaygroundController(PlaygroundService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<PlaygroundDTO> getAll(){
        List<Playground> playground = service.getAll();
        List<PlaygroundDTO> playgroundDTOs = new ArrayList<>();
        Iterator<Playground> it = playground.iterator();
        while(it.hasNext()){
            PlaygroundDTO dto = new PlaygroundDTO();
            dto.setId(it.next().getId());
            dto.setAddress(it.next().getAddress());
            dto.setArea(it.next().getArea());
            dto.setAvailableTime(it.next().getAvailableTime());
            dto.setDescription(it.next().getDescription());
            dto.setName(it.next().getName());
            dto.setPhone(it.next().getPhone());
            dto.setPricePerHour(it.next().getPricePerHour());
            playgroundDTOs.add(dto);
        }
        return playgroundDTOs;
    }

    @GetMapping("/{id}")
    public PlaygroundDTO findOne(@PathVariable("id") Integer id){
        Playground playground = service.getOne(id);
        PlaygroundDTO dto = new PlaygroundDTO();
        dto.setId(playground.getId());
        dto.setName(playground.getName());
        dto.setPhone(playground.getPhone());
        dto.setArea(playground.getArea());
        dto.setAddress(playground.getAddress());
        dto.setPricePerHour(playground.getPricePerHour());
        dto.setAvailableTime(playground.getAvailableTime());
        dto.setDescription(playground.getDescription());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PlaygroundDTO dto) {
        Playground playground = new Playground();
        playground.setName(dto.getName());
        playground.setAddress(dto.getAddress());
        playground.setArea(dto.getArea());
        playground.setAvailableTime(dto.getAvailableTime());
        playground.setDescription(dto.getDescription());
        playground.setPhone(dto.getPhone());
        playground.setPricePerHour(dto.getPricePerHour());
        service.add(playground);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody PlaygroundDTO dto) {
        Playground playground = new Playground();
        playground.setName(dto.getName());
        playground.setAddress(dto.getAddress());
        playground.setArea(dto.getArea());
        playground.setAvailableTime(dto.getAvailableTime());
        playground.setDescription(dto.getDescription());
        playground.setPricePerHour(dto.getPricePerHour());
        playground.setPhone(dto.getPhone());

        service.update(playground);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
