package com.labpos.ExempluLaborator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    public Artist() {
    }

    public Artist(String uuid, String name, boolean active) {
        this.uuid = uuid;
        this.name = name;
        this.active = active;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "content_artists",
            joinColumns = @JoinColumn(name = "id_artist", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "id_content", referencedColumnName = "id")
    )
    public List<Content> contentList = new ArrayList<>();

    public void addToList(Content content){
        this.contentList.add(content);
        content.artistList.add(this);
    }
}
