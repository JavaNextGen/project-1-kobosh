package models;

import java.util.List;
import com.revature.models.*;
import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.IUserService;

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
public class UserService implements IUserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	
	private final IUserDAO userDao;

	  public UserService() {
	    this(new UserDAO());
	  }

	  /* package private for testing */
	  public UserService(IUserDAO userDAO2) {
	    this.userDao = userDAO2;
	    
	  }

	@Override
	public Optional<User> create(User u) {
		IUserDAO ud=new UserDAO();
		//Optional<User >usr=
	return 			ud.create( u);
		//return usr;
	}
	
	@Override
	public Optional< User> getUserById(int id) {
		IUserDAO ud=new UserDAO();
	return	userDao.getUserById(id);
	
	}
	@Override
	public List<User> getUsers() {
		IUserDAO ud=new UserDAO();
		//List<User> l=
	return 			ud.getUsers();
		//return l;
	}
	@Override
	public  Optional< User> getByUsername(String username) {
		IUserDAO ud=new UserDAO();
	Optional<User> usr= userDao.getByUsername(username);
		return usr;
		 
		// @formatter:on

	}	
}
