package com.own.prac.saha.service;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageService {


    @Autowired
    ImgRepository imgRepository;

    public Iterable<ImgContent> getAllImages() {
        return imgRepository.findAll();
    }

    public List<ImgContent> getAllByType(String type) {
        return imgRepository.getAllByType(type);
    }

    public void saveImage(ImgContent content) {
        imgRepository.save(content);
    }


}
