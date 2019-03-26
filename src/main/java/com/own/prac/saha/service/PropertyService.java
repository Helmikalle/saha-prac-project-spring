package com.own.prac.saha.service;

import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    private
    PropertyRepository propertyRepository;

    public PropertyContent getAllSaunaContent() {
        return propertyRepository.findOneByPropertyId("sauna1");
    }

    public void saveImage(PropertyContent content) {
        propertyRepository.save(content);
    }
}
