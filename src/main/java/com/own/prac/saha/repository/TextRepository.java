package com.own.prac.saha.repository;

import com.own.prac.saha.entity.TextContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends CrudRepository <TextContent, Long> {
}
