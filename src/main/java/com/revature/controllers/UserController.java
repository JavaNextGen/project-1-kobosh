package com.revature.controllers;


import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	
		UserDAO ces = new UserDAO();
		
		public Handler getEmployeeUpdateHandler = (ctx)-> {
			
			  if(ctx.req.getSession() != null) {
				  
				  String body = ctx.body();

			        Gson gson = new Gson();

			        User employee = gson.fromJson(body, User.class);

	             Optional<User> allEmployees = ces.create(employee);//.getAllChallengeEmployees();
	              // Gson gson1 = new Gson();
	               String JSONEmployees = gson.toJson(allEmployees);
	               ctx.result(JSONEmployees);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
		
		
		public Handler getEmployeeByIdHandler = (ctx)-> {
			String e_id=ctx.pathParam("e_id");
			  if(ctx.req.getSession() != null) {
	           Optional<  User > allEmployees = ces.getUserById(Integer.parseInt (e_id));//.getAllChallengeEmployees();
	               Gson gson = new Gson();
	               String JSONEmployees = gson.toJson(allEmployees);
	               ctx.result(JSONEmployees);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
		public Handler deleteEmployeeHandler = (ctx)-> {
			String e_id=ctx.pathParam("e_id");
			//System.out.println(e_id[0]);
			
			  if(ctx.req.getSession() != null) {
	              // boolean deleted=
	            		   ces.deleteUser(Integer.parseInt (e_id));
	               
	               ctx.result("deleted");
	               ctx.status(204);

	           } else {
	               ctx.result("Failed to delete employees");
	               ctx.status(404);
	           }
		};
//		TODO: CREATE A CONTROLLER FOR JAVALIN FOR EACH OF THE SERVICES REQUESTEDD
		
		public Handler getEmployeeHandler = (ctx)-> {
			  if(ctx.req.getSession() != null) {
	               List<User> allEmployees = ces.getUsers();
	               Gson gson = new Gson();
	               String JSONEmployees = gson.toJson(allEmployees);
	               ctx.result(JSONEmployees);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
	
public Handler insertEmployeeHandler = (ctx) -> {

    if(ctx.req.getSession() != null) {
        String body = ctx.body();

        Gson gson = new Gson();

      User employee = gson.fromJson(body, User.class);

        ces.create(employee);

        ctx.result("Employee successfully added!");
        ctx.status(201);

    } else {
        ctx.result("You are the weakest link. Goodbye!!!!");
        ctx.status(404);
    }
}
;}

