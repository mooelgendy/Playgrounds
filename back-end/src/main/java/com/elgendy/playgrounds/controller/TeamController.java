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
        Assert.notNull(service, "service must not be null!");
        this.service = service;
    }

    @GetMapping("/")
    public List<Team> getAll(){
        try {
            List<Team> teams = service.getAll();
            return teams;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Team findOne(@PathVariable("id") Integer id){
        try{
            Team team1 = service.getOne(id);
            return team1;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @PostMapping("/")
    public String create(@Valid @RequestBody Team team) {
        try {
            if (team == null) {
                return "Team You Want To Save Must not Be Null!";
            } else if (team == service.getOne(team.getId())) {
                return "Team Is Already Exist";
            }
            service.add(team);
            return "Team created successfully with id: " + team.getId().toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in creating new team: " + ex.toString();
        }

    }

    @PutMapping("/")
    public String update(@Valid @RequestBody Team team) {
        try {
            if (service.getOne(team.getId()) == null) {
                return "Team with id:" + team.getId().toString() + " is Not Found!";
            }
            service.update(team);
            return "Team with id:" + team.getId().toString() + " is updated successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in updating team: " + ex.toString();
        }
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            if (service.getOne(id) == null) {
                return "Team with id:" + id.toString() + " is Not Found!";
            }
            service.delete(id);
            return "Team with id:" + id.toString() + " is deleted successfully";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "Error in deleting team: " + ex.toString();
        }
    }

}
