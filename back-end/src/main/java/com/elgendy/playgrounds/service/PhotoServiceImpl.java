package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Photo;
import com.elgendy.playgrounds.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoRepository repository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository repository) {
        Assert.notNull(repository, "Repository must not be null!");
        this.repository = repository;
    }

    @Override
    public List<Photo> getAll() throws RuntimeException {
        try {
            List<Photo> photoList = repository.findAll();
            return photoList;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public Photo getOne(Integer id) {
        try {
            Photo photoById = repository.findById(id);
            return photoById;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public void add(Photo photo) {
        Assert.notNull(photo, "photo You Want To Save Must Not be Null!");
        try {
            repository.save(photo);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Photo photo) {
        Assert.notNull(photo, "photo You Want To Update Must Not be Null!");
        try {
            repository.update(photo);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "Photo Id Must Not be Null!");
        try {
            Photo deletedPhoto = repository.findById(id);
            Assert.notNull(deletedPhoto, "Photo You Want To Delete is Not Found!");
            repository.delete(deletedPhoto);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
