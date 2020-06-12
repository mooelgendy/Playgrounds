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

    @OneToMany(mappedBy="team")
    private Set<Photo> photos;
    
    @ManyToMany(mappedBy = "teams")
    private Set<User> users;

    public Team() {
    }

    public Team(@NotNull String name, @NotNull String address, String bio, Set<User> users, Set<Photo> photos) {
        this.name = name;
        this.address = address;
        this.bio = bio;
        this.users = users;
        this.photos = photos;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
}
