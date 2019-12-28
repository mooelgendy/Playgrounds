package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Playground;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
            Assert.notEmpty(playgrounds, "Playground's List is Empty");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return playgrounds;
    }

    @Override
    public Playground findById(Integer id) {
        Playground playgroundById = null;
        try{
            Assert.notNull(id, "Playground Id Must Not be Null!");
            playgroundById = em.find(Playground.class, id);
            Assert.notNull(playgroundById, "Playground Not Found!");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return playgroundById;
    }

    @Override
    public void save(Playground playground) {
        try{
            Assert.notNull(playground, "Playground You Want To Save Must Not be Null");
            em.persist(playground);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Playground playground) {
        try{
            Assert.notNull(playground, "Playground You Want To update is Not Found!");
            em.merge(playground);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Playground playground) {
        try{
            Assert.notNull(playground, "Playground You Want To Delete Must Not be Null");
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
