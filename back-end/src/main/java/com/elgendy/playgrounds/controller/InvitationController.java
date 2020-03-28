package com.elgendy.playgrounds.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elgendy.playgrounds.model.Invitation;
import com.elgendy.playgrounds.model.DTO.InvitationDTO;
import com.elgendy.playgrounds.service.InvitationService;

@RestController
public class InvitationController {

	private InvitationService service;

    @Autowired
    public InvitationController(InvitationService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public List<InvitationDTO> getAll(){
        List<Invitation> invitations = service.getAll();
        List<InvitationDTO> invitationDTOs = new ArrayList<>();
        Iterator<Invitation> it = invitations.iterator();
        while(it.hasNext()){
        	InvitationDTO dto = new InvitationDTO();
            dto.setId(it.next().getId());
            dto.setName(it.next().getName());
            dto.setDate(it.next().getDate());
            dto.setExpiryDate(it.next().getExpiryDate());
            invitationDTOs.add(dto);
        }
        return invitationDTOs;
    }

    @GetMapping("/{id}")
    public InvitationDTO findOne(@PathVariable("id") Integer id){
    	Invitation invitation = service.getOne(id);
    	InvitationDTO dto = new InvitationDTO();
        dto.setId(invitation.getId());
        dto.setName(invitation.getName());
        dto.setDate(invitation.getDate());
        dto.setExpiryDate(invitation.getExpiryDate());
        return dto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody InvitationDTO dto) {
    	Invitation invitation = new Invitation();
    	invitation.setName(dto.getName());
    	invitation.setDate(dto.getDate());
    	invitation.setExpiryDate(dto.getExpiryDate());
        service.add(invitation);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody InvitationDTO dto) {
    	Invitation invitation = new Invitation();
    	invitation.setId(dto.getId());
    	invitation.setName(dto.getName());
    	invitation.setDate(dto.getDate());
    	invitation.setExpiryDate(dto.getExpiryDate());
        service.update(invitation);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
