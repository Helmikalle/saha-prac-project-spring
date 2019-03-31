package com.own.prac.saha.repository;

import com.own.prac.saha.entity.ImgContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImgRepository extends JpaRepository<ImgContent, Long> {
    Page<ImgContent> findByPropertyId(String propertyId, Pageable pageable);
    Optional<ImgContent> findByIdAndPropertyId(Long id, String propertyId);
}
