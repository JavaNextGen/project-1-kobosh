package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.User;

public interface IUserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
	 */
	Optional<User> create(User u);

	Optional<User> getUserById(int id);

	List<User> getUsers();

	Optional<User> getByUsername(String username);

}