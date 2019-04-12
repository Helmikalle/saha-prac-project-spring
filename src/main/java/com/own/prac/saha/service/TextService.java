package com.own.prac.saha.service;

import com.own.prac.saha.entity.TextContent;
import com.own.prac.saha.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TextService {

    @Autowired
    TextRepository textRepository;

    public Iterable<TextContent> getAllTexts() {
        return textRepository.findAll();
    }

    public ResponseEntity<TextContent> saveText(TextContent content) {
        textRepository.save(content);
        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/text/{id}")
                .buildAndExpand(content.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
