package com.revature.services;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.User;
import com.revature.repositories.AuthDAO;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;

import java.util.List;
//import java.util.ArrayList;
import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {
	
	
	
	AuthDAO DAO=new AuthDAO();
	static User loggedinUser =null;

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     * @throws Exception 
     */
    public boolean login(String username, String password) throws Exception {
    	
    	return DAO.login(username, password);
    	
    /*List<User> lst=DAO.getUsers();
    //User foundUser=null;
    boolean found=false;
    for(User  u :lst)
    {
    	if (u.getUsername().equals(username) && !u.getPassword().equals(password )) {
			throw new Exception("password does not match");
		}
    	if (u.getPassword().equals(password )&& u.getUsername().equals(username)) {
    		loggedinUser=u;
    		found=true;
			return loggedinUser;
		}
    	
    }
    if(!found) throw new Exception(" user not found");
        return null;*/
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) throws RegistrationUnsuccessfulException {
    	UserDAO DAO=new UserDAO();
    	
    	List<User> lst=DAO.getUsers();
    	    //User foundUser=null;
    	 String email=userToBeRegistered.getEmail();
    	 String username=userToBeRegistered.getEmail();
    	    for(User  u :lst)
    	    {
    	    	if (u.getEmail().equals(email ) || u.getUsername().equals(username)) {
    	    		
    				 throw new RegistrationUnsuccessfulException("registration unsueccessful");
    			}
    	    	
    	    }
    	 return       DAO.create(userToBeRegistered).get();
        
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
    	
    	if(loggedinUser !=null)
    		return Optional.of(loggedinUser);
    	
        return Optional.empty();
    }
}
