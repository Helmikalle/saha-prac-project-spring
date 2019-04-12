package com.own.prac.saha.controller;

import com.own.prac.saha.entity.TextContent;
import com.own.prac.saha.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class TextContentController {

    @Autowired
    TextService textService;

    @GetMapping("/text")
    public Iterable<TextContent> getAllTexts() {
        return textService.getAllTexts();
    }

    @PostMapping("/text")
    public ResponseEntity<TextContent> newText(@Valid @RequestBody TextContent content) {
        return textService.saveText(content);
    }
}
