package com.own.prac.saha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ImgContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name="SAHA_PHOTO_URL", columnDefinition = "text")
    private String sahaPhotoURL;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_content_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PropertyContent propertyContent;

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

    public PropertyContent getPropertyContent() {
        return propertyContent;
    }

    public void setPropertyContent(PropertyContent propertyContent) {
        this.propertyContent = propertyContent;
    }
}
