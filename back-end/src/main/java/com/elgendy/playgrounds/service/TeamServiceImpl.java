package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Team;
import com.elgendy.playgrounds.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository repository;

    @Autowired
    public TeamServiceImpl(TeamRepository repository) {
        Assert.notNull(repository, "Repository must not be null!");
        this.repository = repository;
    }

    @Override
    public List<Team> getAll() throws RuntimeException {
        try {
            List<Team> teamList = repository.findAll();
            return teamList;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public Team getOne(Integer id) {
        try {
            Team teamById = repository.findById(id);
            return teamById;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public void add(Team team) {
        Assert.notNull(team, "Team You Want To Save Must Not be Null!");
        try {
            repository.save(team);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Team team) {
        Assert.notNull(team, "Team You Want To Update Must Not be Null!");
        try {
            repository.update(team);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "Team Id Must Not be Null!");
        try {
            Team deletedTeam = repository.findById(id);
            Assert.notNull(deletedTeam, "Team You Want To Delete is Not Found!");
            repository.delete(deletedTeam);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
