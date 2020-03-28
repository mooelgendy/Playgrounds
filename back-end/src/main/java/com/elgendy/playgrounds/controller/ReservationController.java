package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.ReservationDTO;
import com.elgendy.playgrounds.model.Reservation;
import com.elgendy.playgrounds.service.ReservationService;
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
@RequestMapping("/api/reservations")
public class ReservationController implements Serializable {

    private ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<ReservationDTO> getAll(){
        List<Reservation> reservations = service.getAll();
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        Iterator<Reservation> it = reservations.iterator();
        while(it.hasNext()){
            ReservationDTO dto = new ReservationDTO();
            dto.setId(it.next().getId());
            dto.setHoursNumber(it.next().getHoursNumber());
            dto.setPlayersNeeded(it.next().getPlayersNeeded());
            dto.setReservedTime(it.next().getReservedTime());
            reservationDTOs.add(dto);
        }
        return reservationDTOs;
    }

    @GetMapping("/{id}")
    public ReservationDTO findOne(@PathVariable("id") Integer id){
        Reservation reservation = service.getOne(id);
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setHoursNumber(reservation.getHoursNumber());
        dto.setPlayersNeeded(reservation.getPlayersNeeded());
        dto.setReservedTime(reservation.getReservedTime());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setHoursNumber(reservation.getHoursNumber());
        reservation.setPlayersNeeded(reservation.getPlayersNeeded());
        reservation.setReservedTime(dto.getReservedTime());
        service.add(reservation);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setHoursNumber(reservation.getHoursNumber());
        reservation.setPlayersNeeded(reservation.getPlayersNeeded());
        reservation.setReservedTime(dto.getReservedTime());
        service.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
