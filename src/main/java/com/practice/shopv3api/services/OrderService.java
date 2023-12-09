package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.OrderDTO;
import com.practice.shopv3api.entities.Order;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.entities.User;
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
        User user = this.userRepository.findById(dto.getIdUser()).orElseThrow(
                () -> new ShopApiException("This user couldn't be found in the database"));
        Product product = this.productRepository.findById(dto.getIdProduct()).orElseThrow(
                () -> new ShopApiException("This product couldn't be found in the database"));
        Order newOrder = new Order(dto.getQuantity(), dto.getTotalPrice(), dto.getDate(), user, product);
        this.orderRepository.save(newOrder);
    }

    public List<Order> readOrders() {
        return StreamSupport.stream(this.orderRepository.findAll().spliterator(), false).toList();
    }

    public Order readOrderById(Long orderId) {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new ShopApiException("This order couldn't be found in the database"));
    }

    public Order updateOrder(Long orderId, OrderDTO orderBody) {
        Order order = readOrderById(orderId);

        order.setDate(orderBody.getDate());
        order.setQuantity(orderBody.getQuantity());
        order.setTotalPrice(orderBody.getTotalPrice());

        return this.orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        readOrderById(orderId);
        this.orderRepository.deleteById(orderId);
    }
}
