package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Playground;

import java.util.List;

public interface PlaygroundRepository {

    List<Playground> findAll();

    Playground findById(Integer id);

    void save(Playground playground);

    void update(Playground playground);

    void delete(Playground playground);
}
