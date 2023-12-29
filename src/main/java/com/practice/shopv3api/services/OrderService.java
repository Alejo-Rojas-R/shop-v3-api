package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.OrderDTO;
import com.practice.shopv3api.entities.OrderEntity;
import com.practice.shopv3api.entities.ProductEntity;
import com.practice.shopv3api.entities.UserEntity;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.OrderRepository;
import com.practice.shopv3api.repositories.ProductRepository;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    OrderRepository orderRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void createOrder(OrderDTO dto) {
        UserEntity userEntity = this.userRepository.findById(dto.getIdUser()).orElseThrow(
                () -> new ShopApiException("This user couldn't be found in the database"));
        ProductEntity product = this.productRepository.findById(dto.getIdProduct()).orElseThrow(
                () -> new ShopApiException("This product couldn't be found in the database"));
        OrderEntity newOrderEntity = new OrderEntity(dto.getQuantity(), dto.getTotalPrice(), dto.getDate(), userEntity, product);
        this.orderRepository.save(newOrderEntity);
    }

    public List<OrderEntity> readOrders() {
        return StreamSupport.stream(this.orderRepository.findAll().spliterator(), false).toList();
    }

    public OrderEntity readOrderById(Long orderId) {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new ShopApiException("This order couldn't be found in the database"));
    }

    public OrderEntity updateOrder(Long orderId, OrderDTO orderBody) {
        OrderEntity orderEntity = readOrderById(orderId);

        orderEntity.setDate(orderBody.getDate());
        orderEntity.setQuantity(orderBody.getQuantity());
        orderEntity.setTotalPrice(orderBody.getTotalPrice());

        return this.orderRepository.save(orderEntity);
    }

    public void deleteOrder(Long orderId) {
        readOrderById(orderId);
        this.orderRepository.deleteById(orderId);
    }
}
