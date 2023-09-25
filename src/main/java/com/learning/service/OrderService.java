package com.learning.service;

import com.learning.dto.Cart;
import com.learning.dto.Order;
import com.learning.dto.Product;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import java.util.List;
import java.util.Optional;
import javax.naming.NameNotFoundException;

public interface OrderService {

  public Order checkout(Order order);
  public List<Order> getOrdersByUserId(String userId) throws IdNotFoundException;

  public Optional<Order> getOrdersById(String orderId) throws IdNotFoundException;
}
