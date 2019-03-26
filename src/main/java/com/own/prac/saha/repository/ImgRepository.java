package com.own.prac.saha.repository;

import com.own.prac.saha.entity.ImgContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgRepository extends JpaRepository<ImgContent, Long> {
}
