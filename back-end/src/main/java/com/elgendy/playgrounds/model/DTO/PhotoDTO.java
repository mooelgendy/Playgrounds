package com.elgendy.playgrounds.model.DTO;

public class PhotoDTO {

    private Integer id;

    private String name;

    private String link;

    public PhotoDTO() {
    }

    public PhotoDTO(Integer id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
