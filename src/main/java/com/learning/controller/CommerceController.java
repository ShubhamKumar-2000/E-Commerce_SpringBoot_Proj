package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.PRODUCTTYPE;
import com.learning.dto.Product;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.service.CommerceService;

@RestController
@RequestMapping("/api/product")
public class CommerceController {
	
	@Autowired
	private CommerceService commerceService;

	@PostMapping("")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<?> addProduct(@RequestBody Product product) throws AlreadyExistsException {
		Product result = commerceService.addProduct(product);
		return ResponseEntity.status(201).body(result);
	}
	
//	GET request for retrieving product item by id
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getProductById(@PathVariable("id") String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
		Optional<Product> optional = commerceService.getProductById(id);
		System.out.println("hello");
		return ResponseEntity.ok(optional.get());
	}
	
//	PUT request for updating product item by id
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateProduct(@PathVariable("id") String id, @RequestBody Product product) throws IdNotFoundException {
		Product result = commerceService.modifyProduct(id, product);
		return ResponseEntity.status(200).body(result);
	}
	
//	GET request for retrieving all product items
	@GetMapping("")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllProducts() throws NameNotFoundException, InvalidIdLengthException {
		Optional<List<Product>> optional = commerceService.getAllProducts();
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "No record found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
//	GET request for retrieving product item by product type
	@GetMapping("/type/{type}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getProductByType(@PathVariable("type") PRODUCTTYPE producttype) throws NameNotFoundException, IdNotFoundException, InvalidIdLengthException {
		Optional<Optional<List<Product>>> optional = commerceService.getProductByProductType(producttype);
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Sorry Product Not Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
//	DELETE request for deleting product item by id
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProductById(@PathVariable("id") String id) throws IdNotFoundException {
		commerceService.deleteProduct(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Product item deleted");
		return ResponseEntity.status(200).body(map);
	}

}
