package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEAM")
public class Team implements Serializable {

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

    @Column(name = "BIO")
    private String bio;

    @Column(name = "PROFILE_PHOTO")
    private String profilePhoto;

    @Column(name = "COVER_PHOTO")
    private String coverPhoto;

    @ManyToMany(mappedBy = "teams")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "reservedTeams")
    private Set<Reservation> reservations = new HashSet<>();


    public Team() {
    }

    public Team(@NotNull String name, @NotNull String address, String bio, String profilePhoto, String coverPhoto, Set<User> users, Set<Reservation> reservations) {
        this.name = name;
        this.address = address;
        this.bio = bio;
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.users = users;
        this.reservations = reservations;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}