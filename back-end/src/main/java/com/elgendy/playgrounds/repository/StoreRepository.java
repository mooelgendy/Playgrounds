package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Store;
import java.util.List;

public interface StoreRepository {

    List<Store> findAll();

    Store findById(Integer id);

    void save(Store item);

    void update(Store item);

    void delete(Store item);
}
