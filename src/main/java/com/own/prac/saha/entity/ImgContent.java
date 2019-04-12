package com.own.prac.saha.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ImgContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name="SAHA_PHOTO_URL")
    private String sahaPhotoURL;

    @Column(name = "property_id")
    private String propertyId;

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

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
}
