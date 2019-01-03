package com.own.prac.saha.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends CrudRepository<ImgRepository, Long> {
}
