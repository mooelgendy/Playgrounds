package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "RESERVATION")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "RESERVED_TIME")
    private Date ReservedTime;

    @NotNull
    @Column(name = "PLAYERS_NEEDED")
    private String PlayersNeeded;

    @NotNull
    @Column(name = "HOURS_NUMBER")
    private String HoursNumber;

    @OneToMany(mappedBy="reservation")
    private Set<User> users;
    
    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)
    private Playground playground;
    
    @OneToMany(mappedBy="reservation")
    private Set<Invitation> invitations;

    public Reservation() {
    }

    public Reservation(@NotNull Date reservedTime, @NotNull String playersNeeded, @NotNull String hoursNumber, Set<User> users, Playground playground, Set<Invitation> invitations) {
        this.ReservedTime = reservedTime;
        this.PlayersNeeded = playersNeeded;
        this.HoursNumber = hoursNumber;
        this.users = users;
        this.playground = playground;
        this.invitations = invitations;
    }

    public Integer getId() {
        return id;
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

    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }

	public Set<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<Invitation> invitations) {
		this.invitations = invitations;
	}
}
