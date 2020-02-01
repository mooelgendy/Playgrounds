package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    @PersistenceContext
    EntityManager em;

    @Autowired
    public ReservationRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = null;
        try{
            reservations = em.createQuery("From Reservation", Reservation.class).getResultList();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reservations;
    }

    @Override
    public Reservation findById(Integer id) {
        Reservation reservationById = null;
        try{
            reservationById = em.find(Reservation.class, id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reservationById;
    }

    @Override
    public void save(Reservation reservation) {
        try{
            em.persist(reservation);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Reservation reservation) {
        try{
            em.merge(reservation);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Reservation reservation) {
        try{
            if(em.contains(reservation)){
                em.remove(reservation);
            }else {
                throw new RuntimeException("reservation You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
