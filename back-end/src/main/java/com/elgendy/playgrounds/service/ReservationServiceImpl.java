package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Reservation;
import com.elgendy.playgrounds.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repository) {
        Assert.notNull(repository, "Repository must not be null!");
        this.repository = repository;
    }

    @Override
    public List<Reservation> getAll() throws RuntimeException {
        try {
            List<Reservation> reservationList = repository.findAll();
            return reservationList;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public Reservation getOne(Integer id) {
        try {
            Reservation reservationById = repository.findById(id);
            return reservationById;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public void add(Reservation reservation) {
        Assert.notNull(reservation, "reservation You Want To Save Must Not be Null!");
        try {
            repository.save(reservation);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Reservation reservation) {
        Assert.notNull(reservation, "reservation You Want To Update Must Not be Null!");
        try {
            repository.update(reservation);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "Reservation Id Must Not be Null!");
        try {
            Reservation deletedReservation = repository.findById(id);
            Assert.notNull(deletedReservation, "Reservation You Want To Delete is Not Found!");
            repository.delete(deletedReservation);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
