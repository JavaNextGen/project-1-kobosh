package com.revature.controllers;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;

public class ReimbursementController {




	
		ReimbursementDAO ces = new ReimbursementDAO();
		
		public Handler UpdateHandler = (ctx)-> {
			
			  if(ctx.req.getSession() != null) {
				  
				  String body = ctx.body();

			        Gson gson = new Gson();
//double amount, Date creation, String description,int author, int status,  int r_type)
			        Reimbursement unprocessedReimbursement = gson.fromJson(body, Reimbursement.class);
			        

			        

	               Optional<Reimbursement> reimb = ces.update(unprocessedReimbursement);//.getAllChallengeEmployees();
	              // Gson gson1 = new Gson();
	               String JSONEmployees = gson.toJson(reimb);
	               ctx.result(JSONEmployees);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
		
		
		public Handler getByIdHandler = (ctx)-> {
			
			  if(ctx.req.getSession() != null) {
				  String e_id=ctx.pathParam("e_id");
	             Optional<Reimbursement> allEmployees = ces.getById(Integer.parseInt (e_id));//.getAllChallengeEmployees();
	               Gson gson = new Gson();
	               String JSONEmployees = gson.toJson(allEmployees);
	               ctx.result(JSONEmployees);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
		public Handler deleteHandler = (ctx)-> {
			String e_id=ctx.pathParam("e_id");
			//System.out.println(e_id[0]);
			
			  if(ctx.req.getSession() != null) {
	               boolean deleted= ces.deleteReimbursement(Integer.parseInt (e_id));
	               
	               ctx.result("deleted");
	               ctx.status(204);
           } else {
	               ctx.result("Failed to delete employees");
	               ctx.status(404);
	           }
		};
//		TODO: CREATE A CONTROLLER FOR JAVALIN FOR EACH OF THE SERVICES REQUESTEDD
		
		public Handler getAllHandler = (ctx)-> {
			  if(ctx.req.getSession() != null) {
	               List<Reimbursement> allEmployees = ces.getReimbursements();
	               Gson gson = new Gson();
	               String JSONEmployees = gson.toJson(allEmployees);
	               ctx.result(JSONEmployees);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
	
public Handler createHandler = (ctx) -> {

    if(ctx.req.getSession() != null) {
    	String body = ctx.body();

        Gson gson = new Gson();
//double amount, Date creation, String description,int author, int status,  int r_type)
        Reimbursement emp = gson.fromJson(body, Reimbursement.class);
        String stat=emp.getStatus().toString();
        int st=1;
        if(stat.toLowerCase().equals("resolved")) st=2;
        if(stat.toLowerCase().equals("denid")) st=3;
        String desc=emp.getDescription();
        int auid=emp.getAuthor().getId();
        
        String retype=emp.getReimb_type().toString();
        int tp=1;
        if(retype.toLowerCase().equals("certification")) tp=2;
        double amnt=emp.getAmount();
        Date  created=emp.getCreation();

        

       Optional<Reimbursement> allEmployees = ces.create(amnt,created,desc,auid,st,tp);//.getAllChallengeEmployees();
      // Gson gson1 = new Gson();
       String JSONEmployees = gson.toJson(allEmployees);
       ctx.result(JSONEmployees);
       ctx.status(201);

   } else {
       ctx.result("Failed to reletive employees");
       ctx.status(404);
   }
   

    
}
;}

