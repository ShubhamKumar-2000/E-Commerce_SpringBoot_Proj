package com.learning.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import com.learning.dto.PRODUCTTYPE;
import com.learning.dto.Product;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;

public interface CommerceService {
	
	public Product addProduct(Product product);
	public String deleteProduct(String id) throws IdNotFoundException;
	public Product modifyProduct(String id, Product product) throws IdNotFoundException;
	public Optional<Product> getProductById(String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException;
	public Optional<List<Product>> getAllProducts() throws NameNotFoundException, InvalidIdLengthException;
	public Optional<Optional<List<Product>>> getProductByProductType(PRODUCTTYPE producttype) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException;
	

}
