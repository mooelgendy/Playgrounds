package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PhotoRepositoryImpl implements PhotoRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public PhotoRepositoryImpl(EntityManager em) {
        Assert.notNull(em, "EntityManger Must not be null!");
        this.em = em;
    }

    @Override
    public List<Photo> findAll() {
        List<Photo> photos = em.createQuery("From Photo", Photo.class).getResultList();
        Assert.notEmpty(photos, "List is Empty");
        return photos;
    }

    @Override
    public Photo findById(Integer id) {
        Assert.notNull(id, "Photo Id Must Not be Null!");
        Photo photoById = em.find(Photo.class, id);
        Assert.notNull(photoById, "Photo Not Found!");
        return photoById;
    }

    @Override
    public void save(Photo photo) {
        Assert.notNull(photo, "photo You Want To Save Must Not be Null");
        try{
            em.persist(photo);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Photo photo) {
        Assert.notNull(photo, "photo You Want To update is Not Found!");
        try{
            em.merge(photo);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Photo photo) {
        Assert.notNull(photo, "photo You Want To Delete Must Not be Null");
        try{
            if(em.contains(photo)){
                em.remove(photo);
            }else {
                throw new RuntimeException("photo You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}
