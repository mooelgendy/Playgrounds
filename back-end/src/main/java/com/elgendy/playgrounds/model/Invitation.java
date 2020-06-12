package com.elgendy.playgrounds.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "INVITATION")
public class Invitation {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;
    
    @NotNull
    @Column(name = "INVITATION_DATE")
    private Date date;
    
    @NotNull
    @Column(name = "EXPIRY_DATE")
    private String expiryDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")
    private Reservation reservation;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

	public Invitation() {
		super();
	}

	public Invitation(@NotNull String name, @NotNull Date date, @NotNull String expiryDate, Reservation reservation, User user) {
		super();
		this.name = name;
		this.date = date;
		this.expiryDate = expiryDate;
		this.reservation = reservation;
		this.user = user;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
