package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIO")
    private String bio;

    @ManyToMany(cascade	 = { CascadeType.ALL })
    @JoinTable(name = "USER_TEAM",
               joinColumns = { @JoinColumn(name = "USER_ID") },
               inverseJoinColumns = { @JoinColumn(name = "TEAM_ID") })
    private Set<Team> teams;

    @OneToMany(mappedBy="user")
    private Set<Photo> photos;

    @OneToMany(mappedBy="user")
    private Set<Store> items;

    @OneToMany(mappedBy="user")
    private Set<Reservation> reservations;
    
    @OneToMany(mappedBy="user")
    private Set<Invitation> invitations;

    public User() {
    }

    public User(@NotNull String name, @NotNull String address, String position, String phone, String bio, Set<Team> teams, Set<Photo> photos, Set<Store> items, Set<Reservation> reservations, Set<Invitation> invitations) {
        this.name = name;
        this.address = address;
        this.position = position;
        this.phone = phone;
        this.bio = bio;
        this.teams = teams;
        this.photos = photos;
        this.items = items;
        this.reservations = reservations;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Store> getItems() {
        return items;
    }

    public void setItems(Set<Store> items) {
        this.items = items;
    }

	public Set<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<Invitation> invitations) {
		this.invitations = invitations;
	}

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
