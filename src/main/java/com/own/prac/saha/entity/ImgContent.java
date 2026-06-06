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

    @Lob
    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Lob
    @Column(name = "THUMBNAIL_URL")
    private String thumbnailUrl;

    @Lob
    @Column(name = "CAPTION")
    private String caption;

    @Lob
    @Column(name = "ALT_TEXT")
    private String altText;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    @Column(name = "SOURCE")
    private String source;

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
        return sahaPhotoURL != null ? sahaPhotoURL : imageUrl;
    }

    public void setSahaPhotoURL(String sahaPhotoURL) {
        this.sahaPhotoURL = sahaPhotoURL;
    }

    public String getImageUrl() {
        return imageUrl != null ? imageUrl : sahaPhotoURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
}
