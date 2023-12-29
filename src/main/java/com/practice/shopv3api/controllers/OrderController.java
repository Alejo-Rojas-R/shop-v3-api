package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.OrderDTO;
import com.practice.shopv3api.entities.OrderEntity;
import com.practice.shopv3api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public void createOrder(@RequestBody OrderDTO dto) {
        this.service.createOrder(dto);
    }

    @GetMapping()
    public List<OrderEntity> readOrders(){
        return service.readOrders();
    }

    @GetMapping("{id}")
    public OrderEntity readOrderById(@PathVariable Long id){
        return service.readOrderById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderEntity updateOrder(@PathVariable("id") Long id, OrderDTO dto){
        return service.updateOrder(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@PathVariable("id") Long id){
        service.deleteOrder(id);
    }
}
