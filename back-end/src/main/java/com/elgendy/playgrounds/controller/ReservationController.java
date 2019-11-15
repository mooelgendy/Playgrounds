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
        Assert.notNull(service, "service must not be null!");
        this.service = service;
    }

    @GetMapping("/")
    public List<Reservation> getAll(){
        try {
            List<Reservation> reservations = service.getAll();
            return reservations;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Reservation findOne(@PathVariable("id") Integer id){
        try{
            Reservation reservation1 = service.getOne(id);
            return reservation1;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @PostMapping("/")
    public String create(@Valid @RequestBody Reservation reservation) {
        try {
            if (reservation == null) {
                return "reservation You Want To Save Must not Be Null!";
            } else if (reservation == service.getOne(reservation.getId())) {
                return "reservation Is Already Exist";
            }
            service.add(reservation);
            return "playground created successfully with id: " + reservation.getId().toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in creating new reservation: " + ex.toString();
        }

    }

    @PutMapping("/")
    public String update(@Valid @RequestBody Reservation reservation) {
        try {
            if (service.getOne(reservation.getId()) == null) {
                return "reservation with id:" + reservation.getId().toString() + " is Not Found!";
            }
            service.update(reservation);
            return "reservation with id:" + reservation.getId().toString() + " is updated successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in updating reservation: " + ex.toString();
        }
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            if (service.getOne(id) == null) {
                return "reservation with id:" + id.toString() + " is Not Found!";
            }
            service.delete(id);
            return "reservation with id:" + id.toString() + " is deleted successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in deleting reservation: " + ex.toString();
        }
    }
}
