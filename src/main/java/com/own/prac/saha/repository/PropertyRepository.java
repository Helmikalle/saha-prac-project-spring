package com.own.prac.saha.repository;

import com.own.prac.saha.entity.PropertyContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyContent, Long> {
     PropertyContent findOneByPropertyId(String propertyId);
}
