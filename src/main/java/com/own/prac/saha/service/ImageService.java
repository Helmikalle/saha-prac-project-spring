package com.own.prac.saha.service;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.exception.ResourceNotFoundException;
import com.own.prac.saha.repository.ImgRepository;
import com.own.prac.saha.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageService {

    @Autowired
    private
    ImgRepository imgRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    public Page<ImgContent> getAllImages(String id, Pageable pageable) {
        return imgRepository.findByPropertyId(id, pageable);
    }

    public ImgContent addNewImage(String id, ImgContent content) {
        return propertyRepository.findOneByPropertyId(id).map(post -> {
            content.setPropertyContent(post);
            return imgRepository.save(content);
        }).orElseThrow(() -> new ResourceNotFoundException("PropertId " + id + " not found"));
    }

    public ResponseEntity<?> deleteImage(String propertyId, Long imageId) {
        return imgRepository.findByIdAndPropertyId(imageId, propertyId).map(image -> {
            imgRepository.delete(image);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + imageId + " and propertyId " + propertyId));
    }

    public ImgContent updateImage(String propertyId, Long imageId, ImgContent imageDetails) {
        if(!propertyRepository.existsByPropertyId(propertyId)) {
            throw new ResourceNotFoundException("PropertyId " + propertyId + " not found");
        }

        return imgRepository.findById(imageId).map(image -> {
            image.setSahaPhotoURL(imageDetails.getSahaPhotoURL());
            image.setName(imageDetails.getName());
            image.setPropertyContent(imageDetails.getPropertyContent());
            return imgRepository.save(image);
        }).orElseThrow(() -> new ResourceNotFoundException("ImageId " + imageId + "not found"));
    }
}
