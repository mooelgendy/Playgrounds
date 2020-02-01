package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public PhotoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Photo> findAll() {
        List<Photo> photos = null;
        try{
            photos = em.createQuery("From Photo", Photo.class).getResultList();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return photos;
    }

    @Override
    public Photo findById(Integer id) {
        Photo photoById = null;
        try{
            photoById = em.find(Photo.class, id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return photoById;
    }

    @Override
    public void save(Photo photo) {
        try{
            em.persist(photo);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Photo photo) {
        try{
            em.merge(photo);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Photo photo) {
        try{
            if(em.contains(photo)){
                em.remove(photo);
            }else {
                throw new RuntimeException("photo You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
