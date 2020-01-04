package com.elgendy.playgrounds.model.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Integer id;

    private String name;

    private String address;

    private String position;

    private String phone;

    private String bio;

    private Date chosenTime;

    private String profilePhoto;

    private String coverPhoto;

    private Set<TeamDTO> teams = new HashSet<>();

    private Set<PhotoDTO> photos = new HashSet<>();

    private Set<StoreDTO> items = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String address, String position, String phone, String bio, Date chosenTime, String profilePhoto, String coverPhoto, Set<TeamDTO> teams, Set<PhotoDTO> photos, Set<StoreDTO> items) {
        this.id = id;
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

    public void setId(Integer id) {
        this.id = id;
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

    public Set<TeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamDTO> teams) {
        this.teams = teams;
    }

    public Set<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<PhotoDTO> photos) {
        this.photos = photos;
    }

    public Set<StoreDTO> getItems() {
        return items;
    }

    public void setItems(Set<StoreDTO> items) {
        this.items = items;
    }
}
