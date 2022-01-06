package com.revature.models;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.services.AuthService;

public class RegistrationModel {
	
	
	public  User login(String u,String p) {
		//System.out.println("Please log in");
    	AuthService authService=new AuthService();
    	try {
    		User lgin=authService.login(u, p);
    		return lgin;
    		//System.out.println( "welcome  "+ lgin.getUsername());
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	};
    	// get logged in user info
    	//if (authService.exampleRetrieveCurrentUser().get()!=null) {
			
		
   // System.out.println(	  authService.exampleRetrieveCurrentUser().get().getEmail());
    	//}
	
	return null;
}
public User register(User user)
{
	AuthService authService=new AuthService();
	try {
		User lgin=authService.register(user);
		return lgin;
		//System.out.println( "welcome  "+ lgin.getUsername());
	}catch (RegistrationUnsuccessfulException e) {
		System.out.println(e.getMessage());
	};
return null;
}
}
