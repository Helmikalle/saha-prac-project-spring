package com.own.prac.saha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImgContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
