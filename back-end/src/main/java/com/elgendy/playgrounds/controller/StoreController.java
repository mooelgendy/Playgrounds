package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.exception.ApiNotFoundException;
import com.elgendy.playgrounds.exception.InternalServerErrorException;
import com.elgendy.playgrounds.model.DTO.StoreDTO;
import com.elgendy.playgrounds.model.Store;
import com.elgendy.playgrounds.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private StoreService service;
    private static Logger LOGGER = LoggerFactory.getLogger(StoreController.class);
    private String exceptionMessage = "Error Occurred!";

    @Autowired
    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<StoreDTO> getAll(){
        List<Store> items = null;
        List<StoreDTO> itemsDTOs = null;
        try{
            items = service.getAll();
            itemsDTOs = items.stream().map(item -> {
                StoreDTO dto = new StoreDTO();
                dto.setId(item.getId());
                dto.setName(item.getName());
                dto.setDescription(item.getDescription());
                dto.setSerialNumber(item.getSerialNumber());
                dto.setPrice(item.getPrice());
                return dto;
            }).collect(Collectors.toList());
            return itemsDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
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
            if (e instanceof NullPointerException){
                throw new ApiNotFoundException("Item with Id: "+ id + " is not found");
            } else {
                throw new InternalServerErrorException(exceptionMessage);
            }
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
            throw new InternalServerErrorException(exceptionMessage);
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