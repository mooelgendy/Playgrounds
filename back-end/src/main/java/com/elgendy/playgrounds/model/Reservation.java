package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PLAYGROUND_ID")
    private Playground playground;

    public Reservation() {
    }

    public Reservation(@NotNull Date reservedTime, @NotNull String playersNeeded, @NotNull String hoursNumber, User user, Playground playground) {
        this.ReservedTime = reservedTime;
        this.PlayersNeeded = playersNeeded;
        this.HoursNumber = hoursNumber;
        this.user = user;
        this.playground = playground;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
}
