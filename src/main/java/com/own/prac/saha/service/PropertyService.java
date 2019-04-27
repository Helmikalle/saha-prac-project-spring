package com.own.prac.saha.service;

import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@Service
public class PropertyService {

    @Autowired
    private
    PropertyRepository propertyRepository;

    public Optional<PropertyContent> getAllPropertyContent(String propertyId) {
        return propertyRepository.findOneByPropertyId(propertyId);
    }

    public void deleteProperty(String propertyId) throws Exception {
        Optional<PropertyContent> content = propertyRepository.findOneByPropertyId(propertyId);
        propertyRepository.delete(content.orElseThrow(Exception::new));
    }

    public ResponseEntity<PropertyContent> createNewPropertyContent(PropertyContent content) {
        propertyRepository.save(content);
        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/property/{id}")
                .buildAndExpand(content.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public Iterable<PropertyContent> getAllProperties() {
        return propertyRepository.findAll();
    }
}
