package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> getAll() throws RuntimeException;

    Photo getOne(Integer id);

    void add(Photo photo);

    void update(Photo photo);

    void delete(Integer id);
}
