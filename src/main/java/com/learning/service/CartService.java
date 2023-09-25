package com.learning.service;

import com.learning.dto.Cart;
import com.learning.dto.PRODUCTTYPE;
import com.learning.dto.Product;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import java.util.List;
import java.util.Optional;
import javax.naming.NameNotFoundException;

public interface CartService {

  public Cart addProduct(Product product,String userId);
  public List<Cart> getCartByUserId(String userId) throws IdNotFoundException;
  public String deleteProductByIdFromCart(String id,String userId) throws IdNotFoundException;
  public String clearCart(String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException;

}
