package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.TeamDTO;
import com.elgendy.playgrounds.model.Team;
import com.elgendy.playgrounds.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/team")
public class TeamController implements Serializable {

    private TeamService service;
    private static Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<TeamDTO> getAll(){
        List<Team> teams = null;
        List<TeamDTO> teamDTOs = null;
        Iterator<Team> it = null;
        try{
            teams = service.getAll();
            teamDTOs = new ArrayList<>();
            it = teams.iterator();
            while(it.hasNext()){
                Team team = it.next();
                TeamDTO dto = new TeamDTO();
                dto.setId(team.getId());
                dto.setName(team.getName());
                dto.setAddress(team.getAddress());
                dto.setBio(team.getBio());
                teamDTOs.add(dto);
            }
            return teamDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @GetMapping("/{id}")
    public TeamDTO findOne(@PathVariable("id") Integer id){
        Team team = null;
        TeamDTO dto = null;
        try{
            team = service.getOne(id);
            dto = new TeamDTO();
            dto.setId(team.getId());
            dto.setName(team.getName());
            dto.setAddress(team.getAddress());
            dto.setBio(team.getBio());
            return dto;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody TeamDTO dto) {
        Team team = null;
        try{
            team = new Team();
            team.setName(dto.getName());
            team.setAddress(dto.getAddress());
            team.setBio(dto.getBio());
            service.add(team);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody TeamDTO dto) {
        Team team = null;
        try{
            team = new Team();
            team.setId(dto.getId());
            team.setName(dto.getName());
            team.setAddress(dto.getAddress());
            team.setBio(dto.getBio());
            service.update(team);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        try{
            service.delete(id);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Internal Server Error");
        }
    }
}
