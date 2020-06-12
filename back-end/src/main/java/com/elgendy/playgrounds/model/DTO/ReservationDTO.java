package com.elgendy.playgrounds.model.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReservationDTO {

    private Integer id;

    private String name;

    private Date ReservedTime;

    private String PlayersNeeded;

    private String HoursNumber;

    private PlaygroundDTO playground;

    private UserDTO user;

    private Set<InvitationDTO> invitations = new HashSet<>();

    public ReservationDTO() {
    }

    public ReservationDTO(Integer id, String name, Date reservedTime, String playersNeeded, String hoursNumber, PlaygroundDTO playground, UserDTO user, Set<InvitationDTO> invitations) {
        this.id = id;
        this.name = name;
        ReservedTime = reservedTime;
        PlayersNeeded = playersNeeded;
        HoursNumber = hoursNumber;
        this.playground = playground;
        this.user = user;
        this.invitations = invitations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReservedTime() {
        return ReservedTime;
    }

    public void setReservedTime(Date reservedTime) {
        ReservedTime = reservedTime;
    }

    public String getPlayersNeeded() {
        return PlayersNeeded;
    }

    public void setPlayersNeeded(String playersNeeded) {
        PlayersNeeded = playersNeeded;
    }

    public String getHoursNumber() {
        return HoursNumber;
    }

    public void setHoursNumber(String hoursNumber) {
        HoursNumber = hoursNumber;
    }

    public PlaygroundDTO getPlayground() {
        return playground;
    }

    public void setPlayground(PlaygroundDTO playground) {
        this.playground = playground;
    }

    public Set<InvitationDTO> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<InvitationDTO> invitations) {
        this.invitations = invitations;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
