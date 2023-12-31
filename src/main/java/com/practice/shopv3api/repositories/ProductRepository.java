package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query
    List<Product> findByCategory_Id(Integer categoryId, Pageable pageable);

    @Query
    List<Product> findByNameLikeOrDescriptionLike(String name, String description, Pageable pageable);
}
