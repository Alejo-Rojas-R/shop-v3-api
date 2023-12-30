package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query
    Optional<Category> findByName(String name);
}
