package com.own.prac.saha.repository;

import com.own.prac.saha.entity.ImgContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends CrudRepository<ImgContent, Long> {
}
