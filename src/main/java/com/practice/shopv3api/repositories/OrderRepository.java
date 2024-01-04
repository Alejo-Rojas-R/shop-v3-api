package com.practice.shopv3api.repositories;

import com.practice.shopv3api.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query
    List<Order> findByUserEmail(@RequestParam String email);
}
