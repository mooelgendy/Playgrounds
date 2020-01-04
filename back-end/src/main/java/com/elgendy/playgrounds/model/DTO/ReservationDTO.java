package com.elgendy.playgrounds.model.DTO;

import java.util.Date;

public class ReservationDTO {

    private Integer id;

    private Date ReservedTime;

    private String PlayersNeeded;

    private String HoursNumber;

    private PlaygroundDTO playground;

    public ReservationDTO() {
    }

    public ReservationDTO(Integer id, Date reservedTime, String playersNeeded, String hoursNumber, PlaygroundDTO playground) {
        this.id = id;
        ReservedTime = reservedTime;
        PlayersNeeded = playersNeeded;
        HoursNumber = hoursNumber;
        this.playground = playground;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
