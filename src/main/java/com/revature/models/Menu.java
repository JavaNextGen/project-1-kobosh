package com.revature.models;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.services.UserService;

//import com.revature.repositories.EmployeeDAO;

//This Menu Class will have a displayMenu() method that displays the menu to the user and lets them interact with it
//The menu will make use of the Scanner class to take user inputs in order to travel through the menu options.
public class Menu
{
	
	User loginUser=null;
	User registeredUser=null;
	
	
	  User register()
		{
			String unm=null;
			String pwd=null;
			Scanner scan=new Scanner(System.in);
			System.out.println(" user name ?");
			unm=scan.nextLine();
			System.out.println(" password?");
			pwd=scan.nextLine();
			System.out.println(" first name?");
			String fnm=scan.nextLine();
			System.out.println(" last name?");
			String lnm=scan.nextLine();
			System.out.println(" email?");
			String email=scan.nextLine();
			System.out.println(" role : 1  for  employee  or 2  for office manager?");
			int role=scan.nextInt();
		
		
		
		
			
			
			UserService us=new UserService();
		User  success=us.register(unm,pwd,fnm,lnm,email,role);
			//System.out.println(success);
			//scan.close();
			return success;
		}
	
	
	  User login()
	{
		String unm=null;
		String pwd=null;
		Scanner scan=new Scanner(System.in);
		System.out.println(" user name ?");
		unm=scan.nextLine();
		System.out.println(" password?");
		pwd=scan.nextLine();
	
		
		
		UserService us=new UserService();
	User  success=us.login(unm,pwd);
		//System.out.println(success);
		//scan.close();
		return success;
	}

	//EmployeeDAO eDAO = new EmployeeDAO(); //we need this object to use methods from EmployeeDAO
	
	//All of the menu display options and control flow are contained within this method
	public void displayMenu()
	{
		
		boolean displayMenu = true; //we're going to use this to toggle whether the menu continues after user input
		Scanner scan = new Scanner(System.in); //Scanner object to parse (take) user input
		
		//give the user a pretty greeting :)
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("Welcome to The Krusty Krab Employee Management System");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		while(registeredUser==null || loginUser==null)
		{
			System.out.println("if not registered / logged in ");
			System.out.println("enter  register OR enter login ");
			String log=scan.nextLine();
			switch(log) {
			case "register": while(loginUser== null)
			{
				//System.out.println(" have to Login ");
				registeredUser=register();
				
				
			}
			break;
			
			
			case "login": while(loginUser== null)
			{
				//System.out.println(" have to Login ");
				loginUser=login();
				
				
			}
			break;
			}
		}
		
		//display the menu as long as the displayMenu boolean == true
		//this is going to hold and display all my menu options until displayMenu == false
		while(displayMenu) 
		{ 
			
			
			while(registeredUser== null)
			{
				System.out.println(" have to register ");
				registeredUser=register();
				
				
			}
			
			System.out.println(" welcome " + loginUser.getFname() );
			System.out.println("==============================================================");
			//menu options
			//System.out.println("hi -> get greeted");
			System.out.println("users -> show all users");
			System.out.println("usersbyid -> get users with a certain ID");
			System.out.println("usersbyname -> get users with a certain user name");
			System.out.println("create -> insert user into database");
			System.out.println("exit -> exit the application");
			
			//parse user input after they choose a menu option, and put it into a String variable
			String input = scan.nextLine();
			
			//switch statement that takes the user input and executes the appropriate code
			//BEN MAY ADD MORE COMMENTS HERE ONCE WE DO DATABASE CONNECTIVITY
			switch(input)
			{
			
			/*case "hi": {
				
				loginUser=login();
				System.out.println(loginUser.getRole());
				System.out.println("log in");
				String unm=null;
				String pwd=null;
				
				System.out.println(" user name ?");
				unm=scan.nextLine();
				System.out.println(" password?");
				pwd=scan.nextLine();
				scan.nextLine();
				
				System.out.println();
				
				UserService us=new UserService();
			User  loginUser=us.login(unm,pwd);
				System.out.println(loginUser);
				//break; //we need a break in each case block, or else all the other cases will still run
			} */
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
				scan.close();
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
