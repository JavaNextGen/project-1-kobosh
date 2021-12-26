package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.util.ConnectionFactory;
import com.revature.models.*;

//import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Driver {

    public static void main(String[] args) {
    	
    	Role ro=Role.EMPLOYEE;
    	User us=new User(1,"","",ro);
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection Successful :)");
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
    	
    	Menu  men=new Menu();
    	men.displayMenu();
    }
}
