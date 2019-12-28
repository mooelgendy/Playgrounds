package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Store;

import java.util.List;

public interface StoreService {

    List<Store> getAll();

    Store getOne(Integer id);

    void add(Store item);

    void update(Store item);

    void delete(Integer id);
}
