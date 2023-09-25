package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.PRODUCTTYPE;
import com.learning.dto.Product;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.InvalidIdLengthException;
import com.learning.repository.ProductRepository;
import com.learning.service.CommerceService;

@Service
public class ComemrceServiceImpl implements CommerceService {
	
	@Autowired
	private ProductRepository repository ;
	
	@Override
	//@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Product addProduct(Product product) {
//		repository.findById(product.getId());
//		if(status) {
//			throw new AlreadyExistsException("this record already exists");
//		}
		Product product1 = repository.save(product);
		if (product1 != null) {
			//return "record added in product";
			return product1;
		} else {
			return null;
		}
	}

	@Override
	public String deleteProduct(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Product> optional;
		try {
			optional = this.getProductById(id);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(id);
				return "record deleted";
			}
		} catch (IdNotFoundException | InvalidIdLengthException | NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Product modifyProduct(String id, Product product) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		if(!this.repository.existsById(id))
//			throw new IdNotFoundException("invalid email");
//		
//		return (this.repository.save(product)!= null) ? "success":"fail";
		// TODO Auto-generated method stub
		if (repository.findById(id).isEmpty()) {
			throw new IdNotFoundException("Sorry Not Found");
		}
		return repository.save(product);
	}

	@Override
	public Optional<Product> getProductById(String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Optional<List<Product>> getAllProducts() throws NameNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findAll());
	}

	@Override
	public Optional<Optional<List<Product>>> getProductByProductType(PRODUCTTYPE producttype)
			throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findByProductType(producttype));
	}
    

}