package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;

public class UserService implements IUserService {

	private final IUserDAO userDao;

	  public UserService() {
	    this(new UserDAO());
	  }

	  /* package private for testing */
	public  UserService(IUserDAO userDao) {
	    this.userDao = userDao;
	    // ...
	  }
	@Override
	public Optional<User> create(User u) {
		// TODO Auto-generated method stub
		return userDao.create(u);
	}

	@Override
	public Optional<User> getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	public Optional<User> getByUsername(String username) {
		// TODO Auto-generated method stub
		
		return userDao.getByUsername(username);
	}

}
