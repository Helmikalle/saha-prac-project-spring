package com.own.prac.saha.service;

import com.own.prac.saha.entity.TextContent;
import com.own.prac.saha.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextService {

    @Autowired
    TextRepository textRepository;

    public Iterable<TextContent> getAllTexts() {
        return textRepository.findAll();
    }

    public void saveText(TextContent content) {
        textRepository.save(content);
    }
}
