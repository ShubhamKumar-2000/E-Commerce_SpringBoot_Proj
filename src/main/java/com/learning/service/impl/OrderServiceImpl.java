package com.learning.service.impl;

import com.learning.dto.Order;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.OrderRepository;
import com.learning.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;


  @Override
  public Order checkout(Order order) {
    Order order1 = orderRepository.save(order);
    return order1;
  }

  @Override
  public List<Order> getOrdersByUserId(String userId) throws IdNotFoundException {
    List<Order> orders = orderRepository.findOrderByUserId(userId);
    return orders;
  }

  @Override
  public Optional<Order> getOrdersById(String orderId) throws IdNotFoundException {
    return orderRepository.findById(orderId);
  }
}
