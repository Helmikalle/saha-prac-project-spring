package com.own.prac.saha.controller;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.service.ImageService;
import com.own.prac.saha.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ImageContentController {

    @Autowired
    ImageService imageService;

    @Autowired
    PropertyService propertyService;

    @GetMapping("/image")
    public Iterable<ImgContent> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/sauna")
    @Transactional
    public PropertyContent getSaunaContent() {
        return propertyService.getAllSaunaContent();
    }

    @PostMapping("/sauna")
    public ResponseEntity<PropertyContent> newSaunaProperty(@Valid @RequestBody PropertyContent content) {
        propertyService.saveImage(content);
        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/sauna/{id}")
                .buildAndExpand(content.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/image")
    public ResponseEntity<ImgContent> newImage(@Valid @RequestBody ImgContent content) {
        imageService.saveImage(content);
        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/image/{id}")
                .buildAndExpand(content.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/image/{id}")
    public void deleteImage(@PathVariable("id") long id) {
        imageService.deleteImage(id);
    }
}
