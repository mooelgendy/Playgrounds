package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAll();

    Reservation getOne(Integer id);

    void add(Reservation reservation);

    void update(Reservation reservation);

    void delete(Integer id);
}
