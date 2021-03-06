package com.elgendy.playgrounds.model.DTO;

public class StoreDTO {

    private Integer id;

    private String name;

    private String description;

    private String serialNumber;

    private String price;

    public StoreDTO() {
    }

    public StoreDTO(Integer id, String name, String description, String price, String serialNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.serialNumber = serialNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
