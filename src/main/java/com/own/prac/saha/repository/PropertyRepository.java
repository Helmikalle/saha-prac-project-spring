package com.own.prac.saha.repository;

import com.own.prac.saha.entity.PropertyContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyContent, Long> {
     Optional<PropertyContent> findOneByPropertyId(String propertyId);
}
