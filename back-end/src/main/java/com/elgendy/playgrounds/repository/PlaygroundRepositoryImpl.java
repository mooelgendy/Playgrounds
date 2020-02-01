package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Playground;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PlaygroundRepositoryImpl implements PlaygroundRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public PlaygroundRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Playground> findAll() {
        List<Playground> playgrounds = null;
        try{
            playgrounds = em.createQuery("From Playground", Playground.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return playgrounds;
    }

    @Override
    public Playground findById(Integer id) {
        Playground playgroundById = null;
        try{
            playgroundById = em.find(Playground.class, id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return playgroundById;
    }

    @Override
    public void save(Playground playground) {
        try{
            em.persist(playground);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Playground playground) {
        try{
            em.merge(playground);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Playground playground) {
        try{
            if(em.contains(playground)){
                em.remove(playground);
            }else {
                throw new RuntimeException("Playground You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
