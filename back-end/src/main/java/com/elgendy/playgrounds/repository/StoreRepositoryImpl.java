package com.elgendy.playgrounds.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.elgendy.playgrounds.model.Store;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
            Assert.notEmpty(items, "Item's List is Empty");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return items;
    }

    @Override
    public Store findById(Integer id) {
        Store itemById = null;
        try{
            Assert.notNull(id, "Item Id Must Not be Null!");
            itemById = em.find(Store.class, id);
            Assert.notNull(itemById, "Item Not Found!");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return itemById;
    }

    @Override
    public void save(Store item) {
        try{
            Assert.notNull(item, "Item You Want To Save Must Not be Null");
            em.persist(item);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Store item) {
        try{
            Assert.notNull(item, "Item You Want To update is Not Found!");
            em.merge(item);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Store item) {
        try{
            Assert.notNull(item, "Item You Want To Delete Must Not be Null");
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
