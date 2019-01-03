package com.own.prac.saha.entity;

import javax.persistence.*;

@Entity
public class ImgContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @Column(name="SAHA_PHOTO")
    private byte[] SahaPhoto;

    public ImgContent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getSahaPhoto() {
        return SahaPhoto;
    }

    public void setCategoryPhoto(byte[] sahaPhoto) {
        SahaPhoto = sahaPhoto;
    }
}
