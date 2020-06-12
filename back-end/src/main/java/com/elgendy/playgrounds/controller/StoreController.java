package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.StoreDTO;
import com.elgendy.playgrounds.model.Store;
import com.elgendy.playgrounds.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<StoreDTO> getAll(){
        List<Store> items = null;
        List<StoreDTO> itemsDTO = null;
        Iterator<Store> it = null;
        try{
            items = service.getAll();
            itemsDTO = new ArrayList<>();
            it = items.iterator();
            while(it.hasNext()) {
                StoreDTO dto = new StoreDTO();
                dto.setId(it.next().getId());
                dto.setName(it.next().getName());
                dto.setDescription(it.next().getDescription());
                dto.setSerialNumber(it.next().getSerialNumber());
                dto.setPrice(it.next().getPrice());
                itemsDTO.add(dto);
            }
            return itemsDTO;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @GetMapping("/{id}")
    public StoreDTO findOne(@PathVariable("id") Integer id){
        Store item = null;
        StoreDTO dto = null;
        try{
            item = service.getOne(id);
            dto = new StoreDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setDescription(item.getDescription());
            dto.setSerialNumber(item.getSerialNumber());
            dto.setPrice(item.getPrice());
            return dto;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody StoreDTO dto) {
        Store item = null;
        try{
            item = new Store();
            item.setName(dto.getName());
            item.setDescription(dto.getDescription());
            item.setSerialNumber(dto.getSerialNumber());
            item.setPrice(dto.getPrice());
            service.add(item);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody StoreDTO dto) {
        Store item = null;
        try{
            item = new Store();
            item.setId(dto.getId());
            item.setName(dto.getName());
            item.setDescription(dto.getDescription());
            item.setSerialNumber(dto.getSerialNumber());
            item.setPrice(dto.getPrice());
            service.update(item);
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