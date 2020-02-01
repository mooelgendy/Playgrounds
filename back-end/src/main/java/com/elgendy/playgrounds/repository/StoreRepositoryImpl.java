package com.elgendy.playgrounds.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.elgendy.playgrounds.model.Store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StoreRepositoryImpl implements StoreRepository{

    @PersistenceContext
    EntityManager em;

    @Autowired
    public StoreRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Store> findAll() {
        List<Store> items = null;
        try{
            items = em.createQuery("From Store", Store.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return items;
    }

    @Override
    public Store findById(Integer id) {
        Store itemById = null;
        try{
            itemById = em.find(Store.class, id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return itemById;
    }

    @Override
    public void save(Store item) {
        try{
            em.persist(item);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Store item) {
        try{
            em.merge(item);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Store item) {
        try{
            if(em.contains(item)){
                em.remove(item);
            }else {
                throw new RuntimeException("Item You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
