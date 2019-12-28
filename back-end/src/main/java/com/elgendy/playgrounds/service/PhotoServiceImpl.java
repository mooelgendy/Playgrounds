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
        this.repository = repository;
    }

    @Override
    public List<Photo> getAll() {
        List<Photo> photoList = repository.findAll();
        return photoList;
    }

    @Override
    public Photo getOne(Integer id) {
        Photo photoById = repository.findById(id);
        return photoById;
    }

    @Override
    public void add(Photo photo) {
        repository.save(photo);
    }

    @Override
    public void update(Photo photo) {
        repository.update(photo);
    }

    @Override
    public void delete(Integer id) {
        Photo deletedPhoto = repository.findById(id);
        repository.delete(deletedPhoto);
    }
}
