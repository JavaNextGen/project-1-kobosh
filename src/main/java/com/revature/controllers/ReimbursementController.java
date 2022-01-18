package com.revature.controllers;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.ReimbAppDTO;
import com.revature.models.Reimbursement;
import com.revature.repositories.IReimbursementDAO;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.ReimbursementService;
import com.revature.models.*;

import io.javalin.http.Handler;

public class ReimbursementController {




	
		IReimbursementDAO ces = new ReimbursementDAO();
		 IUserDAO uDao=new UserDAO();
		public Handler applyHandler = (ctx) -> {

		    if(ctx.req.getSession() != null) {
		    	String body = ctx.body();

		        Gson gson = new Gson();
		//double amount, LocalDateTime creation, String description,int author, int status,  int r_type)
		        ReimbAppDTO appDto = gson.fromJson(body, ReimbAppDTO.class);
		        System.out.println("reimb type " + appDto.getReimb_type() + " " + appDto.getDescription());
		       User author= uDao.getByUsername(appDto.getUsername()).get();
		        System.out.println("user id" + author.getId());
		      // appDto.getAmount()

		   LocalDateTime submitted=   LocalDateTime.now();//new LocalDateTime(System.currentTimeMillis());

		       Optional<Reimbursement> reimbApp = ces.create(
		    		   appDto.getAmount()
		    		   ,submitted,
		    		   appDto.getDescription(),
		    		   author.getId(), 
		    		   Status.PENDING.ordinal()+1,
		    		   appDto.getReimb_type().ordinal()+1);
		       String applic = gson.toJson(reimbApp.get());
		       ctx.result(applic);
		       ctx.status(201);

		   } else {
		       ctx.result("Failed to place application");
		       ctx.status(404);
		   }
		   

		    
		}		;
		
		public Handler UpdateHandler = (ctx)-> {
			System.out.println("calling reimb upLocalDateTime ham\ndler");
			  if(ctx.req.getSession() != null) {
				  Reimbursement unprocessedReimbursement=null;
				  String body = ctx.body();
                  /* String[] rebody= body.split("},");
                   for(String s : rebody)
                   { System.out.println(s);       }*/
			        Gson gson = new Gson();
			       // User u=gson.fromJson(us,User.class);
			        System.out.println("calling  update method in  handndler" + body);
//double amount, LocalDateTime creation, String description,int author, int status,  int r_type)
			        try {
			        unprocessedReimbursement = gson.fromJson(body, Reimbursement.class);
			        
			        }catch(Exception e) {System.out.println("error " + e.getMessage());}
			        System.out.println("gson " + unprocessedReimbursement);
	               Optional<Reimbursement> reimb = ces.update(unprocessedReimbursement);//.getAllChallengeEmployees();
	              // Gson gson1 = new Gson();
	               String JSONReimbursements = gson.toJson(reimb.get());
	               ctx.result(JSONReimbursements);
	               ctx.status(200);
			        

	           } else {
	               ctx.result("Failed to reletive employees");
	               ctx.status(404);
	           }
		};
		public Handler getByStatusHandler = (ctx)-> {
			
			  if(ctx.req.getSession() != null) {
				  System.out.println("ststus handler called");
				  String body=ctx.body();
				  Gson gson = new Gson();
				  Status status=gson.fromJson(body, Status.class);
				  System.out.println(status);
	             List<Reimbursement> reimbursements = ces.getBystatus(status);//.getAllChallengeEmployees();
	              
	               String reimbur = gson.toJson(reimbursements);
	               ctx.result(reimbur);
	               ctx.status(200);

	           } else {
	               ctx.result("Failed to retrieve reimbursements");
	               ctx.status(404);
	           }
		};
		
		public Handler getByIdHandler = (ctx)-> {
			
			  if(ctx.req.getSession() != null) {
				  String e_id=ctx.pathParam("e_id");
	             Optional<Reimbursement> allReimbursements = ces.getById(Integer.parseInt (e_id));//.getAllChallengeEmployees();
	               Gson gson = new Gson();
	               String JSONReimbursements = gson.toJson(allReimbursements);
	               ctx.result(JSONReimbursements);
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
	               List<Reimbursement> allReimbursements = ces.getReimbursements();
	               Gson gson = new Gson();
	               String JSONReimbursements = gson.toJson(allReimbursements);
	               ctx.result(JSONReimbursements);
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
//double amount, LocalDateTime creation, String description,int author, int status,  int r_type)
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
        LocalDateTime  created=emp.getCreation();

        

       Optional<Reimbursement> allReimbursements = ces.create(amnt,created,desc,auid,st,tp);//.getAllChallengeEmployees();
      // Gson gson1 = new Gson();
       String JSONReimbursements = gson.toJson(allReimbursements);
       ctx.result(JSONReimbursements);
       ctx.status(201);

   } else {
       ctx.result("Failed to reletive employees");
       ctx.status(404);
   }
   

    
}
;


}

