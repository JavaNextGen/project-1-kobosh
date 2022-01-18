package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import com.revature.models.User;

public interface IUserDAO {

	Optional<User> getUserById(int id);

	Optional<User> getByUsername(String uname);

	List<User> getUsers();

	Optional<User> create(User newUser);

	boolean deleteUser(int userId);

	Optional<User> update(User employee);

}