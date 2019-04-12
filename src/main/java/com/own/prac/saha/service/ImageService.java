package com.own.prac.saha.service;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Service
public class ImageService {

    @Autowired
    private
    ImgRepository imgRepository;

    public Iterable<ImgContent> getAllImages() {
        return imgRepository.findAll();
    }

    public void deleteImage(Long id) {
        imgRepository.deleteById(id);
    }

    public ResponseEntity<ImgContent> addNewImage(ImgContent content) {
        imgRepository.save(content);
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
