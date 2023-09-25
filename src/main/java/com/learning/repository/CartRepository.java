package com.learning.repository;

import com.learning.dto.Cart;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

  List<Cart> findCartByUserId(String userId);
  Optional<Cart> findCartByProductId(String productId);


}
