package com.own.prac.saha.controller;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ImageContentController {

    @Autowired
    ImageService imageService;

    @GetMapping("/image")
    public Iterable<ImgContent> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/image/type")
    public List<ImgContent> getAllByType(@RequestParam String type) {
        return imageService.getAllByType(type);
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
}
