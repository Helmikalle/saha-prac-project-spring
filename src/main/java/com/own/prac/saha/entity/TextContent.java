package com.own.prac.saha.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class TextContent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @Column(name = "PARAGRAPH")
    private String paragraph;

    @Column(name = "TYPE")
    private String type;

    public TextContent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
