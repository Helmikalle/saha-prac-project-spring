package com.own.prac.saha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class PropertyContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "property_id")
    private String propertyId;

    @Lob
    private String paragraph;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id", referencedColumnName = "property_id")
    private List<ImgContent> imageList;

    public PropertyContent() {
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

    public List<ImgContent> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImgContent> imageList) {
        this.imageList = imageList;
    }
}
