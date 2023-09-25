package com.learning.controller;

import com.learning.dto.Cart;
import com.learning.dto.Product;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.service.CartService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> addProductToCart(@RequestBody Product product,@PathVariable String userId) throws AlreadyExistsException {
    Cart result = cartService.addProduct(product,userId);
    return ResponseEntity.status(201).body(result);
  }

  @GetMapping("/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> getCartByUserId (@PathVariable String userId) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
    List<Cart> cart = cartService.getCartByUserId(userId);
    System.out.println("hello");
    return ResponseEntity.ok(cart);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> deleteProductByIdFromCart(@PathVariable("id") String id, @RequestParam String userId) throws IdNotFoundException {
    cartService.deleteProductByIdFromCart(id,userId);
    Map<String, String> map = new HashMap<String, String>();
    map.put("message", "Product item deleted");
    return ResponseEntity.status(200).body(map);
  }

  @DeleteMapping("/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> clearCart(@PathVariable("userId") String id)
      throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
    cartService.clearCart(id);
    Map<String, String> map = new HashMap<String, String>();
    map.put("message", "Product item deleted");
    return ResponseEntity.status(200).body(map);
  }

}
