package com.own.prac.saha.controller;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.exception.ResourceNotFoundException;
import com.own.prac.saha.repository.ImgRepository;
import com.own.prac.saha.repository.PropertyRepository;
import com.own.prac.saha.service.ImageService;
import com.own.prac.saha.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private
    ImageService imageService;

    @GetMapping("/property/{id}/images")
    public Page<ImgContent> getAllImagesByPropertyId(@PathVariable (value = "id") String id,
                                                     Pageable pageable) {
        return imageService.getAllImages(id, pageable);
    }

    @PutMapping("property/{propertyId}/image/{imageId}")
    public ImgContent updateUser(@PathVariable(value = "propertyId") String propertyId,
                                                 @PathVariable(value = "imageId") Long imageId,
                                                 @Valid @RequestBody ImgContent imageDetails) {
        return imageService.updateImage(propertyId, imageId, imageDetails);
    }

    @PostMapping("/property/{id}/images")
    public ImgContent newImage(@PathVariable (value = "id") String id,
                                               @Valid @RequestBody ImgContent content) {
        return imageService.addNewImage(id, content);
    }

    @DeleteMapping("property/{propertyId}/image/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable(name = "propertyId") String propertyId, @PathVariable(name = "id") long imageId) {
        return imageService.deleteImage(propertyId, imageId);
    }
}
