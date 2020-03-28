package com.elgendy.playgrounds.controller;

import com.elgendy.playgrounds.model.DTO.TeamDTO;
import com.elgendy.playgrounds.model.Team;
import com.elgendy.playgrounds.service.TeamService;
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
@RequestMapping("/api/teams")
public class TeamController implements Serializable {

    private TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<TeamDTO> getAll(){
        List<Team> teams = service.getAll();
        List<TeamDTO> teamDTOs = new ArrayList<>();
        Iterator<Team> it = teams.iterator();
        while(it.hasNext()){
            TeamDTO dto = new TeamDTO();
            dto.setId(it.next().getId());
            dto.setName(it.next().getName());
            dto.setAddress(it.next().getAddress());
            dto.setBio(it.next().getBio());
            dto.setProfilePhoto(it.next().getProfilePhoto());
            dto.setCoverPhoto(it.next().getCoverPhoto());
            teamDTOs.add(dto);
        }
        return teamDTOs;
    }

    @GetMapping("/{id}")
    public TeamDTO findOne(@PathVariable("id") Integer id){
        Team team = service.getOne(id);
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setAddress(team.getAddress());
        dto.setBio(team.getBio());
        dto.setProfilePhoto(team.getProfilePhoto());
        dto.setCoverPhoto(team.getCoverPhoto());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody TeamDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setAddress(dto.getAddress());
        team.setBio(dto.getBio());
        team.setProfilePhoto(dto.getProfilePhoto());
        team.setCoverPhoto(dto.getCoverPhoto());
        service.add(team);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody TeamDTO dto) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setAddress(dto.getAddress());
        team.setBio(dto.getBio());
        team.setProfilePhoto(dto.getProfilePhoto());
        team.setCoverPhoto(dto.getCoverPhoto());
        service.update(team);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
