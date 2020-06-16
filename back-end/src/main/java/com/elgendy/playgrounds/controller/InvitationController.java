package com.elgendy.playgrounds.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.elgendy.playgrounds.exception.ApiNotFoundException;
import com.elgendy.playgrounds.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.elgendy.playgrounds.model.Invitation;
import com.elgendy.playgrounds.model.DTO.InvitationDTO;
import com.elgendy.playgrounds.service.InvitationService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/invitation")
public class InvitationController {

	private InvitationService service;
    private static Logger LOGGER = LoggerFactory.getLogger(InvitationController.class);
    private String exceptionMessage = "Error Occurred!";

    @Autowired
    public InvitationController(InvitationService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public List<InvitationDTO> getAll(){
        List<Invitation> invitations = null;
        List<InvitationDTO> invitationDTOs = null;
        try{
            invitations = service.getAll();
            invitationDTOs = invitations.stream().map(invitation -> {
                InvitationDTO dto = new InvitationDTO();
                dto.setId(invitation.getId());
                dto.setName(invitation.getName());
                dto.setDate(invitation.getDate());
                dto.setExpiryDate(invitation.getExpiryDate());
                return dto;
            }).collect(Collectors.toList());
            return invitationDTOs;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }
    }

    @GetMapping("/{id}")
    public InvitationDTO findOne(@PathVariable("id") Integer id){
        Invitation invitation = null;
        InvitationDTO dto = null;
        try{
            invitation = service.getOne(id);
            dto = new InvitationDTO();
            dto.setId(invitation.getId());
            dto.setName(invitation.getName());
            dto.setDate(invitation.getDate());
            dto.setExpiryDate(invitation.getExpiryDate());
            return dto;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            if (e instanceof NullPointerException){
                throw new ApiNotFoundException("Invitation with Id: "+ id + " is not found");
            } else {
                throw new InternalServerErrorException(exceptionMessage);
            }
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody InvitationDTO dto) {
        Invitation invitation = null;
        try{
            invitation = new Invitation();
            invitation.setName(dto.getName());
            invitation.setDate(dto.getDate());
            invitation.setExpiryDate(dto.getExpiryDate());
            service.add(invitation);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody InvitationDTO dto) {
        Invitation invitation = null;
        try{
            invitation = new Invitation();
            invitation.setId(dto.getId());
            invitation.setName(dto.getName());
            invitation.setDate(dto.getDate());
            invitation.setExpiryDate(dto.getExpiryDate());
            service.update(invitation);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        try{
            service.delete(id);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new InternalServerErrorException(exceptionMessage);
        }
    }
}
