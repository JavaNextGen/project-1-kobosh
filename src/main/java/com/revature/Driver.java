package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import com.revature.models.Menu;
import com.revature.repositories.ReimbursementDAO;
import com.revature.util.ConnectionFactory;
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
    	ReimbursementDAO dao=new ReimbursementDAO();
    	dao.create(1000.00,null,"travel to NY", 13,Status.PENDING ,ReimbType.TRAVEL );
    	
    	
    	Menu  men=new Menu();
    	//men.displayMenu();
    }
}
