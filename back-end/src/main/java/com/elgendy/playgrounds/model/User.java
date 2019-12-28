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

    @Column(name = "CHOSEN_TIME")
    private Date chosenTime;

    @Column(name = "PROFILE_PHOTO")
    private String profilePhoto;

    @Column(name = "COVER_PHOTO")
    private String coverPhoto;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "USER_TEAM",
               joinColumns = { @JoinColumn(name = "USER_ID") },
               inverseJoinColumns = { @JoinColumn(name = "TEAM_ID") })
    private Set<Team> teams = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "USER_PHOTO",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PHOTO_ID") })
    private Set<Photo> photos = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "USER_STORE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "STORE_ID") })
    private Set<Store> items = new HashSet<>();

    public User() {
    }

    public User(@NotNull String name, @NotNull String address, String position, String phone, String bio, Date chosenTime, String profilePhoto, String coverPhoto, Set<Team> teams, Set<Photo> photos, Set<Store> items) {
        this.name = name;
        this.address = address;
        this.position = position;
        this.phone = phone;
        this.bio = bio;
        this.chosenTime = chosenTime;
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.teams = teams;
        this.photos = photos;
        this.items = items;
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

    public Date getChosenTime() {
        return chosenTime;
    }

    public void setChosenTime(Date chosenTime) {
        this.chosenTime = chosenTime;
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
}
