package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.ReservationDTO;
import com.elgendy.playgrounds.model.Reservation;
import com.elgendy.playgrounds.service.ReservationService;
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
@RequestMapping("/api/reservation")
public class ReservationController implements Serializable {

    private ReservationService service;
    private static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<ReservationDTO> getAll(){
        List<Reservation> reservations = null;
        List<ReservationDTO> reservationDTOs = null;
        Iterator<Reservation> it = null;
        try{
            reservations = service.getAll();
            reservationDTOs = new ArrayList<>();
            it = reservations.iterator();
            while(it.hasNext()){
                ReservationDTO dto = new ReservationDTO();
                dto.setId(it.next().getId());
                dto.setName(it.next().getName());
                dto.setHoursNumber(it.next().getHoursNumber());
                dto.setPlayersNeeded(it.next().getPlayersNeeded());
                dto.setReservedTime(it.next().getReservedTime());
                reservationDTOs.add(dto);
            }
            return reservationDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @GetMapping("/{id}")
    public ReservationDTO findOne(@PathVariable("id") Integer id){
        Reservation reservation = null;
        ReservationDTO dto = null;
        try{
            reservation = service.getOne(id);
            dto = new ReservationDTO();
            dto.setId(reservation.getId());
            dto.setName(reservation.getName());
            dto.setHoursNumber(reservation.getHoursNumber());
            dto.setPlayersNeeded(reservation.getPlayersNeeded());
            dto.setReservedTime(reservation.getReservedTime());
            return dto;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody ReservationDTO dto) {
        Reservation reservation = null;
        try{
            reservation = new Reservation();
            reservation.setName(dto.getName());
            reservation.setHoursNumber(dto.getHoursNumber());
            reservation.setPlayersNeeded(dto.getPlayersNeeded());
            reservation.setReservedTime(dto.getReservedTime());
            service.add(reservation);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody ReservationDTO dto) {
        Reservation reservation = null;
        try{
            reservation = new Reservation();
            reservation.setName(dto.getName());
            reservation.setHoursNumber(dto.getHoursNumber());
            reservation.setPlayersNeeded(dto.getPlayersNeeded());
            reservation.setReservedTime(dto.getReservedTime());
            service.update(reservation);
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
