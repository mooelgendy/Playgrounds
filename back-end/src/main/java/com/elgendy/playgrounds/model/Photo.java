package com.elgendy.playgrounds.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "PHOTO")
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "Link")
    private String link;

    @ManyToOne
    @JoinColumn(name="ID", nullable=false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name="ID", nullable=false)
    private Team team;
    
    @ManyToOne
    @JoinColumn(name="ID", nullable=false)
    private Store store;
    
    @ManyToOne
    @JoinColumn(name="ID", nullable=false)
    private Playground playground;

    public Photo() {
    }

    public Photo(String name, @NotNull String link, User user) {
        this.name = name;
        this.link = link;
        this.user = user;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
