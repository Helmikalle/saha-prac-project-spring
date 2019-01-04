package com.own.prac.saha.entity;

import javax.persistence.*;

@Entity
public class ImgContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name="SAHA_PHOTO_URL")
    private String sahaPhotoURL;

    @Column(name = "TYPE")
    private String type;

    public ImgContent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSahaPhotoURL() {
        return sahaPhotoURL;
    }

    public void setSahaPhotoURL(String sahaPhotoURL) {
        this.sahaPhotoURL = sahaPhotoURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
