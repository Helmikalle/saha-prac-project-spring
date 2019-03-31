package com.own.prac.saha.repository;

import com.own.prac.saha.entity.PropertyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyContent, Long> {
     Optional<PropertyContent> findOneByPropertyId(String propertyId);
     boolean existsByPropertyId(String propertyId);
}
