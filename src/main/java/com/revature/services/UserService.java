package com.revature.services;

import java.util.List;
import com.revature.models.*;
import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> create(User u) {
		UserDAO ud=new UserDAO();
		Optional<User >usr=ud.create( u);
		return usr;
	}
	
	public Optional<User> getUserById(int id) {
		UserDAO ud=new UserDAO();
		Optional<User> usr=ud.getUserById(id);
		return usr;
	}
	public List<User> getUsers() {
		UserDAO ud=new UserDAO();
		List<User> l=ud.getUsers();
		return l;
	}
	public Optional<User> getByUsername(String username) {
		UserDAO ud=new UserDAO();
		List<User> l=ud.getUsers();
		for(User u:l)
		
		if (u.getUsername().equals(username) ){
			return Optional.ofNullable(u);
		}
		return Optional.empty();
	}

	public User login(String unm, String pwd) {
		// TODO Auto-generated method stub
		UserDAO ud=new UserDAO();
		return  ud.login(unm,pwd);
		
	}

	public  Optional< User> register(String unm, String pwd, String fnm, String lnm, String email, int role) {
		// TODO Auto-generated method stub
		UserDAO ud=new UserDAO();
		Optional<User> u=  ud.register(unm,pwd,fnm,lnm,email,role);
		return u;
		 
	}
}
