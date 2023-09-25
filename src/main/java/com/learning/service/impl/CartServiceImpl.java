package com.learning.service.impl;

import com.learning.dto.Cart;
import com.learning.dto.Product;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.repository.CartRepository;
import com.learning.service.CartService;
import java.util.List;
import java.util.Optional;
import javax.naming.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepository cartRepository;


  @Override
  public Cart addProduct(Product product, String userId) {
    Cart cart = Cart.builder()
                    .userId(userId)
                    .productCost(product.getProductCost())
                    .productId(product.getId())
                    .productName(product.getProductName())
                    .build();

    Cart response = cartRepository.save(cart);
    return response;
  }

  @Override
  public List<Cart> getCartByUserId(String userId) throws IdNotFoundException {
    return cartRepository.findCartByUserId(userId);
  }

  @Override
  public String deleteProductByIdFromCart(String id, String userId) throws IdNotFoundException {
    Optional<Cart> cart = cartRepository.findCartByProductId(id);
    if (cart.isPresent()) {
      cartRepository.deleteById(cart.get().getId());
      return "DELETED";
    }
    return "No such product found";
  }

  @Override
  public String clearCart(String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
    List<Cart> cartList = cartRepository.findCartByUserId(id);
    if (cartList.size() > 0) {
      for (int i = 0; i < cartList.size(); i++) {
        cartRepository.deleteById(cartList.get(i).getId());
      }
    }
    return "Completed";
  }
}
