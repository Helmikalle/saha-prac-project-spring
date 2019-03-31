package com.own.prac.saha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PropertyContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PROPERTY_ID")
    private String propertyId;

    @Lob
    private String paragraph;

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
}
