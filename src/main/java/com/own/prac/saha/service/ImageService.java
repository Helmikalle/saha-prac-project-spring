package com.own.prac.saha.service;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private static String UPLOAD_ROOT = "upload-dir";

    @Autowired
    ImgRepository imgRepository;
    @Autowired
    ResourceLoader resourceLoader;

    public Iterable<ImgContent> getAllImages() {
        return imgRepository.findAll();
    }

    public void saveImage(ImgContent content) {
        imgRepository.save(content);
    }

    public Resource findOneImage(String imgName) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + imgName);
    }
}
