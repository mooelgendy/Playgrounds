package com.elgendy.playgrounds.model.DTO;

public class TeamDTO {

    private Integer id;

    private String name;

    private String address;

    private String bio;

    private String profilePhoto;

    private String coverPhoto;

    public TeamDTO() {
    }

    public TeamDTO(Integer id, String name, String address, String bio, String profilePhoto, String coverPhoto) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bio = bio;
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
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
}
