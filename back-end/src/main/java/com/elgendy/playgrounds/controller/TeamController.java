package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.Team;
import com.elgendy.playgrounds.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/teams")
public class TeamController implements Serializable {

    private TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Team> getAll(){
        List<Team> teams = service.getAll();
        return teams;
    }

    @GetMapping("/{id}")
    public Team findOne(@PathVariable("id") Integer id){
        Team team = service.getOne(id);
        return team;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody Team team) {
        service.add(team);
    }

    @PutMapping("/")
    public void update(@Valid @RequestBody Team team) {
        service.update(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
