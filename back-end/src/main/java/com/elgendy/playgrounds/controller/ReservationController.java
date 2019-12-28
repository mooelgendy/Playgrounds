package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.Reservation;
import com.elgendy.playgrounds.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
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
    public List<Reservation> getAll(){
        List<Reservation> reservations = service.getAll();
        return reservations;
    }

    @GetMapping("/{id}")
    public Reservation findOne(@PathVariable("id") Integer id){
        Reservation reservation = service.getOne(id);
        return reservation;
    }

    @PostMapping("/")
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
