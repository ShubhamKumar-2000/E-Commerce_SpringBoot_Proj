package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional(rollbackFor = AlreadyExistsException.class)
	public User addUser(User register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		if(userRepo.existsByEmail(register.getEmail())) {
			throw new AlreadyExistsException("This record already exists");
		}
		return userRepo.save(register);
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepo.findAll());
	}

	@Override
	public Optional<User> getUserById(Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepo.findById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException("Sorry user with "+ id +" not found");
		}
		return optional;
	}

	@Override
	public User updateUser(User register, Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if (userRepo.findById(id).isEmpty()) {
			throw new IdNotFoundException("Sorry user with "+ id +" not found");
		}
		return userRepo.save(register);
	}

	@Override
	@Transactional(rollbackFor = IdNotFoundException.class)
	public String deleteUser(Long id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> optional = this.getUserById(id);
		if (optional.isEmpty())
			throw new IdNotFoundException("Sorry user with "+ id +" not found");
		else {
			userRepo.deleteById(id);
			return "Success";
		}
	}

}
//package com.learning.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.learning.dto.Login;
//import com.learning.dto.User;
//import com.learning.exception.AlreadyExistsException;
//import com.learning.exception.IdNotFoundException;
//import com.learning.repository.LoginRepository;
//import com.learning.repository.UserRepository;
//import com.learning.service.LoginService;
//import com.learning.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private LoginService loginService;
//	@Autowired
//	private LoginRepository loginRepository;
//	
//	@Override
//	@Transactional(rollbackFor = AlreadyExistsException.class)
//	public User addUser(User register) throws AlreadyExistsException {
//		// TODO Auto-generated method stub
//		if(userRepository.existsByEmail(register.getEmail())) {
//			throw new AlreadyExistsException("This record already exists");
//		}
//		register.setPassword(register.getPassword());
//		User register2 = userRepository.save(register);
//		if (register2!=null) {
//			Login login = new Login(register2.getEmail(), register2.getPassword(), register2);
//			if (loginRepository.existsByUsername(login.getUsername())) {
//				throw new AlreadyExistsException("This record already exists");
//			}
//			Login result = loginService.addCredentials(login);
//			if (result!=null)
//				return register2;
//			else
//				return null;
//		}
//		else
//			return null;
//	}
//	
//	@Override
//	public Optional<User> getUserById(Long id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<User> optional = userRepository.findById(id);
//		if (optional.isEmpty()) {
//			throw new IdNotFoundException("Id does not exist");
//		}
//		return optional;
//	}
//
//	@Override
//	public User[] getAllUsers() {
//		// TODO Auto-generated method stub
//		List<User> list = userRepository.findAll();
//		User[] registers = new User[list.size()];
//		return list.toArray(registers);
//	}
//
//	@Override
//	public String deleteUserById(Long id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<User> optional = this.getUserById(id);
//		if (optional.isEmpty())
//			throw new IdNotFoundException("Record not found");
//		else {
//			userRepository.deleteById(id);
//			return "Success";
//		}
//	}
//	
//	@Override
//	public Optional<List<User>> getAllUserDetails() {
//		// TODO Auto-generated method stub
//		return Optional.ofNullable(userRepository.findAll());
//	}
//	
//	@Override
//	public User updateUser(User register) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		if(userRepository.findById(register.getId()).isEmpty()) {
//			throw new IdNotFoundException("Record not found");
//		}
//		return userRepository.save(register);
//	}
//
//}
