package com.own.prac.saha.service;

import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.exception.ResourceNotFoundException;
import com.own.prac.saha.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private
    PropertyRepository propertyRepository;

    public Optional<PropertyContent> getOnePropertyContent(String id) {
        return propertyRepository.findOneByPropertyId(id);
    }

    public PropertyContent addNewProperty(PropertyContent content) {
        return propertyRepository.save(content);
    }

    public Page<PropertyContent> getAll(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    public PropertyContent updateProperty(String propertyId, PropertyContent content) {
        return propertyRepository.findOneByPropertyId(propertyId).map(property -> {
            property.setParagraph(content.getParagraph());
            property.setPropertyId(content.getPropertyId());
            return propertyRepository.save(property);
        }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + propertyId + " not found"));
    }

    public ResponseEntity<?> deleteProperty(String propertId) {
        return propertyRepository.findOneByPropertyId(propertId).map(property -> {
            propertyRepository.delete(property);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + propertId + " not found"));
    }
}

