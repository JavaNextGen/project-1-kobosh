package com.revature.models;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.services.UserService;

//import com.revature.repositories.EmployeeDAO;

//This Menu Class will have a displayMenu() method that displays the menu to the user and lets them interact with it
//The menu will make use of the Scanner class to take user inputs in order to travel through the menu options.
public class Menu {

	//EmployeeDAO eDAO = new EmployeeDAO(); //we need this object to use methods from EmployeeDAO
	
	//All of the menu display options and control flow are contained within this method
	public void displayMenu() {
		
		boolean displayMenu = true; //we're going to use this to toggle whether the menu continues after user input
		Scanner scan = new Scanner(System.in); //Scanner object to parse (take) user input
		
		//give the user a pretty greeting :)
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("Welcome to The Krusty Krab Employee Management System");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		
		
		//display the menu as long as the displayMenu boolean == true
		//this is going to hold and display all my menu options until displayMenu == false
		while(displayMenu) { 
			
			//menu options
			System.out.println("hi -> get greeted");
			System.out.println("users -> show all users");
			System.out.println("usersbyid -> get users with a certain ID");
			System.out.println("usersbyname -> get users with a certain user name");
			System.out.println("create -> insert user into database");
			System.out.println("exit -> exit the application");
			
			
			//parse user input after they choose a menu option, and put it into a String variable
			String input = scan.nextLine();
			
			//switch statement that takes the user input and executes the appropriate code
			//BEN MAY ADD MORE COMMENTS HERE ONCE WE DO DATABASE CONNECTIVITY
			switch(input) {
			
			case "hi": {
				System.out.println("Hello there.");
				break; //we need a break in each case block, or else all the other cases will still run
			}
			case "create": {
				System.out.println("inserting user ");
				System.out.println("enter  user name ");
				String unm=scan.nextLine();
				System.out.println("enter  password ");
				String pwd=scan.nextLine();
				System.out.println("enter  first name ");
				String fnm=scan.nextLine();
				System.out.println("enter  last name ");
				String lnm=scan.nextLine();
				System.out.println("enter  email ");
				String eml=scan.nextLine();
				System.out.println("enter  role id ");
				int rolid=scan.nextInt();
				Role ro=Role.EMPLOYEE;
				if (rolid==2) {
					ro=Role.FINANCE_MANAGER;
				}
				UserService us=new UserService();
				//get the List of users from the repository layer
				User u=new User(0,unm,pwd,fnm,lnm,eml,ro);
				User usr=us.create( u);
				System.out.println(usr);
				break; //we need a break in each case block, or else all the other cases will still run
			}
			
			case "users" :{
				
				System.out.println("functionality tbd");
				UserService us=new UserService();
				//get the List of users from the repository layer
				List<User> users =us.getUsers();
				
				//enhanced for loop to print out the users one by one
				for (User e : users) {
					System.out.println(e);
				}
				
				break;
			}
			
			case "usersbyname" :{
				System.out.println("enter user name");
				String username=scan.nextLine();
				UserService us=new UserService();
				Optional<User> usr=us.getByUsername(username);
				System.out.println(usr);
				break;
			}
			case "usersbyid" :{
				System.out.println("enter user id");
				int userid=scan.nextInt();
				scan.nextLine();
				UserService us=new UserService();
				User usr=us.getUserById(userid);
				System.out.println(usr);
				break;
			}
			case "exit": {
				displayMenu = false;
				break;
			}
			
			//this default block will catch any user inputs that don't match a valid menu option
			default: {
				System.out.println("Invalid selection... try again :'( ");
				break;
			}
			
			
			} //end of switch
			
		} //end of while loop
		
		
	}
	
}