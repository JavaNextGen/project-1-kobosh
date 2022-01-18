package com.revature.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.RegisterDTO;
import com.revature.models.ReimbAppDTO;
import com.revature.models.User;
import com.revature.repositories.AuthDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {
	
	UserDAO as = new UserDAO();
	AuthDAO asd=new AuthDAO();

	public Handler loginHandler = (ctx) -> {
		
		//what's the request body? (which we get from ctx.body) it's the data that gets sent in with a request
		//GET requests will have empty request bodies, but POST requests, which send data, will have some data.
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		System.out.println(body);
		Gson gson = new Gson(); //create a new Gson object to make Java <-> JSON conversions
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); //turn that JSON String into a LoginDTO object
		
		System.out.println(LDTO.getUserName() + LDTO.getPassword());
		//control flow to determine what happens in the event of successful/unsuccessful login
		//invoke the login() method of the AuthService using the username and password from the LoginDTO
		if(asd.login(LDTO.getUserName(), LDTO.getPassword())) {
			System.out.println("called controller");
			//create a user session so that they can access the applications other functionalities
		//HttpSession srq=
				ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
		ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
		//this was supposed to let us do further requests after establishing a session... 
		//but Ben didn't figure it out in time
			
			//return a successful status code 
			ctx.status(202); //202 - accepted. (but you could use any 200 level status code)
			
			//send a message relaying the success
			ctx.result(" successful " );//" session id " +srq.getId());
			
		} else {
			System.out.println("log in failed");
			ctx.status(401); //"unauthorized" status code
			ctx.result("Login Failed! :(");
			
		}
		
	};
public Handler registerHandler = (ctx) -> {
		
		//what's the request body? (which we get from ctx.body) it's the data that gets sent in with a request
		//GET requests will have empty request bodies, but POST requests, which send data, will have some data.
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson(); //create a new Gson object to make Java <-> JSON conversions
		
	User user = gson.fromJson(body, User.class); //turn that JSON String into a LoginDTO object
	System.out.println("USER name "+ user);
	           Optional<User> newUser=as.create(user);
		//control flow to determine what happens in the event of successful/unsuccessful login
		//invoke the login() method of the AuthService using the username and password from the LoginDTO
		if(newUser.get()!=null) {
			System.out.println("called register");
			//create a user session so that they can access the applications other functionalities
		//HttpSession srq=
				ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
		ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
		//this was supposed to let us do further requests after establishing a session... 
		//but Ben didn't figure it out in time
			
			//return a successful status code 
			ctx.status(201); //202 - accepted. (but you could use any 200 level status code)
			
			//send a message relaying the success
			ctx.result(" successful " );//" session id " +srq.getId());
			
		} else {
			
			ctx.status(401); //"unauthorized" status code
			ctx.result("register Failed! :(");
			
		}
		
	};

}