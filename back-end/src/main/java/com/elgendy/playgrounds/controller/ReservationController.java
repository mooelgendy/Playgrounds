package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.PlaygroundDTO;
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
        PlaygroundDTO playgroundDTO = new PlaygroundDTO();
        Iterator<Reservation> it = reservations.iterator();
        while(it.hasNext()){
            ReservationDTO dto = new ReservationDTO();
            dto.setId(it.next().getId());
            dto.setHoursNumber(it.next().getHoursNumber());
            dto.setPlayersNeeded(it.next().getPlayersNeeded());
            playgroundDTO.setId(it.next().getPlayground().getId());
            playgroundDTO.setName(it.next().getPlayground().getName());
            playgroundDTO.setDescription(it.next().getPlayground().getDescription());
            playgroundDTO.setAvailableTime(it.next().getPlayground().getAvailableTime());
            playgroundDTO.setPricePerHour(it.next().getPlayground().getPricePerHour());
            playgroundDTO.setAddress(it.next().getPlayground().getAddress());
            playgroundDTO.setArea(it.next().getPlayground().getArea());
            playgroundDTO.setPhone(it.next().getPlayground().getPhone());
            dto.setPlayground(playgroundDTO);

            reservationDTOs.add(dto);
        }
        return reservationDTOs;
    }

    @GetMapping("/{id}")
    public Reservation findOne(@PathVariable("id") Integer id){
        Reservation reservation = service.getOne(id);
        ReservationDTO dto = new ReservationDTO();
        PlaygroundDTO playgroundDTO = new PlaygroundDTO();
        dto.setId(reservation.getId());
        dto.setHoursNumber(reservation.getHoursNumber());
        dto.setPlayersNeeded(reservation.getPlayersNeeded());
        playgroundDTO.setId(reservation.getPlayground().getId());
        playgroundDTO.setName(reservation.getPlayground().getName());
        playgroundDTO.setDescription(reservation.getPlayground().getDescription());
        playgroundDTO.setAvailableTime(reservation.getPlayground().getAvailableTime());
        playgroundDTO.setPricePerHour(reservation.getPlayground().getPricePerHour());
        playgroundDTO.setAddress(reservation.getPlayground().getAddress());
        playgroundDTO.setArea(reservation.getPlayground().getArea());
        playgroundDTO.setPhone(reservation.getPlayground().getPhone());
        dto.setPlayground(playgroundDTO);
        return reservation;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Reservation reservation) {
        service.add(reservation);
    }

    @PutMapping("/")
    public void update(@Valid @RequestBody Reservation reservation) {
        service.update(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
