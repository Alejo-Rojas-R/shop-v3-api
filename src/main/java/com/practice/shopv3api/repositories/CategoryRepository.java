package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    @Query
    Optional<CategoryEntity> findByName(String name);
}
