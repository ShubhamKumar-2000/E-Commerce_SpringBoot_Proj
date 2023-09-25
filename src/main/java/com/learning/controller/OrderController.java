package com.learning.controller;

import com.learning.dto.Order;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.service.OrderService;
import java.util.List;
import java.util.Optional;
import javax.naming.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("/checkout")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> checkout(@RequestBody Order order) throws AlreadyExistsException {
    Order result = orderService.checkout(order);
    return ResponseEntity.status(201).body(result);
  }

  @GetMapping("/getOrders/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> getOrdersByUserId(@PathVariable String userId)
      throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
    List<Order> order = orderService.getOrdersByUserId(userId);
    System.out.println("hello");
    return ResponseEntity.ok(order);
  }

  @GetMapping("/{orderId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> getOrderByOrderId(@PathVariable String orderId)
      throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
    Optional<Order> order = orderService.getOrdersById(orderId);
    System.out.println("hello");
    if (order.isPresent()) {
      return ResponseEntity.ok(order.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

}
