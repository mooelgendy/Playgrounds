package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    private String Area;

    @Column(name = "AVAILABLE_TIME")
    private String AvailableTime;

    @Column(name = "PRICE_PER_HOUR")
    private String pricePerHour;

    public Playground() {
    }

    public Playground(@NotNull String name, @NotNull String address, String description, String phone, String area, String availableTime, String pricePerHour) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
        Area = area;
        AvailableTime = availableTime;
        this.pricePerHour = pricePerHour;
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
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAvailableTime() {
        return AvailableTime;
    }

    public void setAvailableTime(String availableTime) {
        AvailableTime = availableTime;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}