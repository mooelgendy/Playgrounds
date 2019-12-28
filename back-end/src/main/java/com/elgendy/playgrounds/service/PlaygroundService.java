package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Playground;

import java.util.List;

public interface PlaygroundService {

    List<Playground> getAll();

    Playground getOne(Integer id);

    void add(Playground playground);

    void update(Playground playground);

    void delete(Integer id);
}
