package com.own.prac.saha.controller;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.service.ImageService;
import com.own.prac.saha.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PropertyContentController {

    @Autowired
    private
    ImageService imageService;

    @Autowired
    private
    PropertyService propertyService;

    /**
     *
     * @return all properties
     */
    @GetMapping("/property")
    public Iterable<PropertyContent> getAllProperties() {
        return propertyService.getAllProperties();
    }

    /**
     *
     * @param propertyId
     * @return all images and data for that property
     */

    @GetMapping("/property/{propertyId}")
    @Transactional
    public ResponseEntity<PropertyContent> getPropertyContent(@PathVariable(name = "propertyId") String propertyId) {
        Optional<PropertyContent> propertyContent = propertyService.getAllPropertyContent(propertyId);
        return propertyContent
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     *
     * @param content
     * @return 201 created
     */

    @PostMapping("/property")
    public ResponseEntity<PropertyContent> newPropertyContent(@Valid @RequestBody PropertyContent content) {
        return propertyService.createNewPropertyContent(content);
    }

    /**
     *
      * @param propertyId
     */

    @DeleteMapping("/property/{propertyId}")
    public ResponseEntity<Void> deleteAllForProperty(@PathVariable(name = "propertyId") String propertyId) {
        if (!propertyService.deleteProperty(propertyId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param content
     * @return  201 created
     */

    @PostMapping("/image")
    public ResponseEntity<ImgContent> newImage(@Valid @RequestBody ImgContent content) {
        return imageService.addNewImage(content);
    }

    /**
     *
     * @param id
     * Delete one image
     */

    @DeleteMapping("/image/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") long id) {
        if (!imageService.deleteImage(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
