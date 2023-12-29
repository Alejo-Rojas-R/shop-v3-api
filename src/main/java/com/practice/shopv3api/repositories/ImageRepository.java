package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.ImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    @Query
    List<ImageEntity> findByProductId(Long id);

    @Query
    List<ImageEntity> findImagesByProductId(Long id);
}
