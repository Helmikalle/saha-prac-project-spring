package com.own.prac.saha.controller;

import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PropertyContentController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/property")
    public Page <PropertyContent> getAllProperties(Pageable pageable) {
        return propertyService.getAll(pageable);
    }

    @GetMapping("/property/{id}")
    public Optional<PropertyContent> getPropertyContent(@PathVariable(value = "id") String id) {
        return propertyService.getOnePropertyContent(id);
    }

    @PostMapping("/property")
    public PropertyContent addNewProperty(@Valid @RequestBody PropertyContent content) {
        return propertyService.addNewProperty(content);
    }

    @PutMapping("/property/{propertyId}")
    public PropertyContent updateProperty(@PathVariable(name = "propertyId") String propertyId,
                                          @Valid @RequestBody PropertyContent content) {
        return propertyService.updateProperty(propertyId, content);
    }

    @DeleteMapping("/property/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable(name = "propertyId") String propertId) {
        return propertyService.deleteProperty(propertId);
    }
}
