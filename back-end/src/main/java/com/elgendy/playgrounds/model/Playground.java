package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PLAYGROUND")
public class Playground implements Serializable {

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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "AREA")
    private String area;

    @Column(name = "AVAILABLE_TIME")
    private String availableTime;

    @Column(name = "PRICE_PER_HOUR")
    private String pricePerHour;
    
    @OneToMany(mappedBy="playground")
    private Set<Photo> photos;
    
    @OneToMany(mappedBy="playground")
    private Set<Reservation> reservations;

    public Playground() {
    }

    public Playground(@NotNull String name, @NotNull String address, String description, String phone, String area, String availableTime, String pricePerHour, Set<Photo> photos, Set<Reservation> reservations) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
        this.area = area;
        this.availableTime = availableTime;
        this.pricePerHour = pricePerHour;
        this.photos = photos;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
}
