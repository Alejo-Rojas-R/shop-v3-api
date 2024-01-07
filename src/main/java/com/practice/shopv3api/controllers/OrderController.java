package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.OrderDTO;
import com.practice.shopv3api.entities.Order;
import com.practice.shopv3api.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Order createOrder(@RequestBody OrderDTO dto) {
        return this.service.createOrder(dto);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Order> readOrders(){
        return service.readOrders();
    }

    @GetMapping("{id}")
    public Order readOrderById(@PathVariable Long id){
        return service.readOrderById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Order updateOrder(@PathVariable("id") Long id, OrderDTO dto){
        return service.updateOrder(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteOrder(@PathVariable("id") Long id){
        service.deleteOrder(id);
    }

    @PostMapping("by-user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Order> readOrderByUserEmail(@RequestParam("email") String email) {
        return service.readOrderByUserEmail(email);
    }
}
