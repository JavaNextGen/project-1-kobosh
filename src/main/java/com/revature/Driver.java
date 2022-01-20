package com.revature;

//import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
//import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.management.remote.JMXConnectionNotification;

import com.revature.repositories.AuthDAO;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.*;

//import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Driver {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
    	
    	Role ro=Role.EMPLOYEE;
    	//User us=new User(1,"","",ro);
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection Successful :)");
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
    	
    	
    	//Status er=Status.values()[0];
    	//System.out.println(er );
    	//Optional<Reimbursement>  reim=getreimb(1);
    	// System.out.println(getBystatus(Status.APPROVED).get(0));
    	
    	//System.out.println(update(reim.get()).get());
    	
    	UserDAO o=new UserDAO();
    	//System.out.println(o.getUserById(5));//o.getByUsername("hassanm"));
   // Optional<User> u=	o.create(new User(0,"gen1","pwd1",Role.EMPLOYEE));
   // System.out.println(u);
    	 //Menu m=new Menu();
     //  User u=	m.login();//register();
      // System.out.println(u);
    	UserController  cec=new UserController();
    	 // IMPLEMENT JAVALIN HERE
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(3000);  
        //employee api
       
      app.get("/employee/{e_id}", cec.getEmployeeByIdHandler);
       //app.get("/employee", cec.getEmployeeHandler);
       app.get("/employee/name/{name}", cec.getEmployeeByNameHandler);
       // app.post("/employee", cec.insertEmployeeHandler);
        //app.put("/employee/update", cec.getEmployeeUpdateHandler);
        //app.delete("/employee/{e_id}", cec.deleteEmployeeHandler);
        
        //reimbursement api
       ReimbursementController rec=new ReimbursementController();
        app.get("/reimbursement", rec.getAllHandler);
        app.get("/reimbursement/{e_id}", rec.getByIdHandler);
        app.post("/reimbursement/stat", rec.getByStatusHandler);
        app.post("/apply", rec.applyHandler);
        app.put("/reimbursement/update",rec.UpdateHandler);
       

        
        //athenticate
        AuthController au=new AuthController();
    	app.post("/login",au.loginHandler);
    	app.post("/register",au.registerHandler);
    	
    	////////////////////testing date////////////////////
    	
    	
       /* AuthDAO d=new AuthDAO();
      System.out.println(d.login("hassanm","123"));
       UserDAO ud=new UserDAO();
     //  ud.getUserById(0);*/
      ReimbursementDAO res=new ReimbursementDAO();
  //List<Reimbursement> lr=  res.getReimbursements();
    //Reimbursement r=lr.get(6);
    
    //  res.update(r);
  
   //System.out.println(lr.get(6));
    //insertWithJavaTimeAPI();
    //getResultSetWithJavaSqlAPI() ;
       
    }
private static void  createreimb()
{
	ReimbursementDAO res=new ReimbursementDAO();
	LocalDateTime dt= LocalDateTime.now();//(System.currentTimeMillis());
	//Reimbursement reimb=new Reimbursement(0,Status.PENDING,new User(2,"hassanm","123",Role.EMPLOYEE),null,198.00 );
    Optional<Reimbursement> resm=res.create(298.00,dt ,"travel to Tx",2,1,1);
    System.out.println(resm);
}
private static Optional<Reimbursement>  getreimb(int id)
{
	ReimbursementDAO res=new ReimbursementDAO();
	Optional<Reimbursement> resm=res.getById(id);//.create(298.00,dt ,"travel to NJ",2,1,1);
    return resm;
}

private static Optional<Reimbursement>  update(Reimbursement unprocessedReimbursement)
{
	ReimbursementDAO res=new ReimbursementDAO();
	 Optional<Reimbursement> resm=res.update(unprocessedReimbursement);//.getById(id);//.create(298.00,dt ,"travel to NJ",2,1,1);
    return resm;
}
private static List<Reimbursement>  getBystatus(Status s)
{
	ReimbursementDAO res=new ReimbursementDAO();
	 List<Reimbursement> resm=res.getBystatus(s);//update(unprocessedReimbursement);//.getById(id);//.create(298.00,dt ,"travel to NJ",2,1,1);
    return resm;
}


	/*private static void login() {
		System.out.println("Please log in");
    	AuthService authService=new AuthService();
    	try {
    		User lgin=authService.login("hassanm", "123");
    		//System.out.println( "welcome  "+ lgin.getUsername());
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	};
    	// get logged in user info
    	if (authService.exampleRetrieveCurrentUser().get()!=null) {
			
		
    System.out.println(	  authService.exampleRetrieveCurrentUser().get().getEmail());
    	}
	}*/
}
