package com.own.prac.saha.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

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

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private PropertyContent propertyContent;

    @JsonCreator
    public ImgContent() {
    }

    public ImgContent(long id, String name, String sahaPhotoURL, PropertyContent propertyContent) {
        this.id = id;
        this.name = name;
        this.sahaPhotoURL = sahaPhotoURL;
        this.propertyContent = propertyContent;
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

    public PropertyContent getPropertyContent() {
        return propertyContent;
    }

    public void setPropertyContent(PropertyContent propertyContent) {
        this.propertyContent = propertyContent;
    }
}
