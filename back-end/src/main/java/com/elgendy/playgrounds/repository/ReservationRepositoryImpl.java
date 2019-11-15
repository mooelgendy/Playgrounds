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
        Assert.notNull(em, "EntityManager must not be null!");
        this.em = em;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = em.createQuery("From Reservation", Reservation.class).getResultList();
        Assert.notEmpty(reservations, "List is Empty");
        return reservations;
    }

    @Override
    public Reservation findById(Integer id) {
        Assert.notNull(id, "Reservation Id Must Not be Null!");
        Reservation reservationById = em.find(Reservation.class, id);
        Assert.notNull(reservationById, "Reservation Not Found!");
        return reservationById;
    }

    @Override
    public void save(Reservation reservation) {
        Assert.notNull(reservation, "reservation You Want To Save Must Not be Null");
        try{
            em.persist(reservation);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Reservation reservation) {
        Assert.notNull(reservation, "reservation You Want To update is Not Found!");
        try{
            em.merge(reservation);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Reservation reservation) {
        Assert.notNull(reservation, "reservation You Want To Delete Must Not be Null");
        try{
            if(em.contains(reservation)){
                em.remove(reservation);
            }else {
                throw new RuntimeException("reservation You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}
