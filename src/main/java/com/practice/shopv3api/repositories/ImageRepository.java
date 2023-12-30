package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    @Query
    List<Image> findByProductId(Long id);

    @Query
    List<Image> findImagesByProductId(Long id);
}
