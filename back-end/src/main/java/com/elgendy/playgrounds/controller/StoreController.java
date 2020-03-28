package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.StoreDTO;
import com.elgendy.playgrounds.model.Store;
import com.elgendy.playgrounds.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
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
    public List<StoreDTO> getAll(){
        List<Store> items = service.getAll();
        List<StoreDTO> itemsDTO = new ArrayList<>();
        Iterator<Store> it = items.iterator();
        while(it.hasNext()) {
            StoreDTO dto = new StoreDTO();
            dto.setName(it.next().getName());
            dto.setDescription(it.next().getDescription());
            dto.setPrice(it.next().getPrice());
            dto.setQuantity(it.next().getQuantity());
            itemsDTO.add(dto);
        }
        return itemsDTO;
    }

    @GetMapping("/{id}")
    public StoreDTO findOne(@PathVariable("id") Integer id){
        Store item = service.getOne(id);
        StoreDTO dto = new StoreDTO();
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody StoreDTO dto) {
        Store item = new Store();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        service.add(item);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody StoreDTO dto) {
        Store item = new Store();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        service.update(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}