package com.learning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.PRODUCTTYPE;
import com.learning.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	Optional<List<Product>> findByProductType(PRODUCTTYPE productType);
	

}
