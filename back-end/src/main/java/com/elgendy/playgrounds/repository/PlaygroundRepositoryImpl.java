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
        Assert.notNull(em, "EntityManger Must not be null!");
        this.em = em;
    }

    @Override
    public List<Playground> findAll() {
        List<Playground> playgrounds = em.createQuery("From Playground", Playground.class).getResultList();
        Assert.notEmpty(playgrounds, "List is Empty");
        return playgrounds;
    }

    @Override
    public Playground findById(Integer id) {
        Assert.notNull(id, "Playground Id Must Not be Null!");
        Playground playgroundById = em.find(Playground.class, id);
        Assert.notNull(playgroundById, "Playground Not Found!");
        return playgroundById;
    }

    @Override
    public void save(Playground playground) {
        Assert.notNull(playground, "Playground You Want To Save Must Not be Null");
        try{
            em.persist(playground);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Playground playground) {
        Assert.notNull(playground, "Playground You Want To update is Not Found!");
        try{
            em.merge(playground);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Playground playground) {
        Assert.notNull(playground, "Playground You Want To Delete Must Not be Null");
        try{
            if(em.contains(playground)){
                em.remove(playground);
            }else {
                throw new RuntimeException("Playground You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}
