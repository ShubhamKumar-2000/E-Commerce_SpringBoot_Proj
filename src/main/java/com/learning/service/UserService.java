package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface UserService {

	public User addUser(User register) throws AlreadyExistsException;

	public Optional<User> getUserById(Long id) throws IdNotFoundException;

	public Optional<List<User>> getAllUsers();

	public User updateUser(User user, Long id) throws IdNotFoundException;

	public String deleteUser(Long id) throws IdNotFoundException;

}
