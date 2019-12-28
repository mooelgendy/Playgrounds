package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
            Assert.notEmpty(reservations, "List is Empty");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reservations;
    }

    @Override
    public Reservation findById(Integer id) {
        Reservation reservationById = null;
        try{
            Assert.notNull(id, "Reservation Id Must Not be Null!");
            reservationById = em.find(Reservation.class, id);
            Assert.notNull(reservationById, "Reservation Not Found!");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reservationById;
    }

    @Override
    public void save(Reservation reservation) {
        try{
            Assert.notNull(reservation, "reservation You Want To Save Must Not be Null");
            em.persist(reservation);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Reservation reservation) {
        try{
            Assert.notNull(reservation, "reservation You Want To update is Not Found!");
            em.merge(reservation);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Reservation reservation) {
        try{
            Assert.notNull(reservation, "reservation You Want To Delete Must Not be Null");
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
