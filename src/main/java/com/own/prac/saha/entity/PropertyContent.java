package com.own.prac.saha.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PropertyContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String propertyId;

    @Lob
    private String paragraph;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "propertyContent")
    private Set<ImgContent> imageList;

    @JsonCreator
    public PropertyContent() {
    }

    public PropertyContent(long id, String propertyId, String paragraph, Set<ImgContent> imageList) {
        this.id = id;
        this.propertyId = propertyId;
        this.paragraph = paragraph;
        this.imageList = imageList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public Set<ImgContent> getImageList() {
        return imageList;
    }

    public void setImageList(Set<ImgContent> imageList) {
        this.imageList = imageList;
    }
}
