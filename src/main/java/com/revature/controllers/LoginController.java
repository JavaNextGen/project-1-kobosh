package com.revature.controllers;

import com.revature.services.AuthService;

import io.javalin.http.Handler;
import models.UserService;

public class LoginController {
	
	AuthService es = new AuthService();

	// *****This layer is where we'll parse our JSON into Java objects and vice vera*****
	// Sits between the Javalin Front Controller and the Service Layer
	// We'll either be getting data from the service layer (which is our DAO)
	// ORR sending data to the service layer (will probably return some response that it was successful)
	
	public Handler getEmployeesHandler = (ctx) -> {
		if(ctx.req.getSession() != null)
		{ //if the session exist
			
			/*List<Employee> allEmployees = es.;
			
			// Add the dependency into your pom.xml so it can import the Gson library
			Gson gson = new Gson();
			
			// Use gson library to convert the java object to a JSON string
			String JSONEmployees = gson.toJson(allEmployees);
			
			// Give a response body with a JSON string 
			ctx.result(JSONEmployees);*/
			ctx.status(200);
			

		} else {
			ctx.result("Oh no you failed to get the employees!!!!");
			ctx.status(404);
		}
	};
	


}
