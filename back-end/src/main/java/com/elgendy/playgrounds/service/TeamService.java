package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAll();

    Team getOne(Integer id);

    void add(Team team);

    void update(Team team);

    void delete(Integer id);
}
