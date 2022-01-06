package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.*;

//import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Driver {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
    	
    	Role ro=Role.EMPLOYEE;
    	User us=new User(1,"","",ro);
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection Successful :)");
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
    	 Menu m=new Menu();
       User u=	m.login();//register();
       System.out.println(u);
    	/*UserController  cec=new UserController();
    	 // IMPLEMENT JAVALIN HERE
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(3000);  
        //employee api
       
      app.get("/employee/{e_id}", cec.getEmployeeByIdHandler);
       app.get("/employee", cec.getEmployeeHandler);
        app.post("/employee", cec.insertEmployeeHandler);
        app.put("/employee/update", cec.getEmployeeUpdateHandler);
        app.delete("/employee/{e_id}", cec.deleteEmployeeHandler);
        
        //reimbursement api
       ReimbursementController rec=new ReimbursementController();
        app.get("/reimbursement", rec.getAllHandler);
        app.get("/reimbursement/{e_id}", rec.getByIdHandler);
    	//Menu  men=new Menu();  
    	//men.displayMenu();
        ReimbursementDAO  o=new ReimbursementDAO();
        Optional<Reimbursement> r=o.getById(4);
      //  User u= new User( 1,"kop","123",Role.EMPLOYEE);
       //   int id, String username, String password, Role role)
        //System.out.println(r.get());
        
        UserDAO d=new UserDAO();
      //  System.out.println(d.getUserById(2).get().getUsername());
       */
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
