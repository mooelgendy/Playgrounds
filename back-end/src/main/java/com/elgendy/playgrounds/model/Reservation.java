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
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "RESERVED_TIME")
    private Date ReservedTime;

    @NotNull
    @Column(name = "PLAYERS_NEEDED")
    private String PlayersNeeded;

    @NotNull
    @Column(name = "HOURS_NUMBER")
    private String HoursNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYGROUND_ID", referencedColumnName = "ID")
    private Playground playground;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @OneToMany(mappedBy="reservation")
    private Set<Invitation> invitations;

    public Reservation() {
    }

    public Reservation(@NotNull String name, @NotNull Date reservedTime, @NotNull String playersNeeded, @NotNull String hoursNumber, Playground playground, User user, Set<Invitation> invitations) {
        this.name = name;
        ReservedTime = reservedTime;
        PlayersNeeded = playersNeeded;
        HoursNumber = hoursNumber;
        this.playground = playground;
        this.user = user;
        this.invitations = invitations;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
