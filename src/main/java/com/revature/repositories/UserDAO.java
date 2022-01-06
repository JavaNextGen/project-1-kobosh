package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDAO {
	
public  Optional< User> getUserById(int id){ //This will use SQL SELECT functionality

		
		try(Connection conn = ConnectionFactory.getConnection())
		{ //all of my SQL stuff will be within this try block
			
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			
			//write the query that we want to send to the database, and assign it to a String
			String sql ="select * from ers_users  where user_id = ?;"
					;// "SELECT user_name FROM ers_users  where user_id=?;";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			//Put the SQL query into a Statement object (The Connection object has a method for this!!)
			pst.setInt(1, id);
			//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
			//The Statement object has a method that takes Strings to execute as a SQL query
rs=pst.executeQuery();
		while(rs.next())
				{
				//int ud=rs.getInt("user_id");
					int rd=rs.getInt("role_id");
						Role ro=Role.EMPLOYEE;
						if(rd==2)
							ro=Role.FINANCE_MANAGER;
				
						
						String	unm=null;
						try {
						unm=rs.getString("user_name");
						//System.out.println("user name " + unm);
						}catch(Exception e) {System.out.println(e.getMessage());};
					
					String	email=rs.getString("email");
					String	fname=rs.getString("fname");
					String	lname=rs.getString("lname");
					String	pwd=rs.getString("user_password");
						User user=new User(id,unm,pwd,ro);//fname,lname,email,ro);
						return Optional.of( user);
				
					
					
				}
				}catch(SQLException e) {
					System.out.println("error !!?");
					e.printStackTrace();
					
					
				}
			
			
			return null;
		}

public  User   getByUsername(String uname) 
{
	try(Connection conn = ConnectionFactory.getConnection())
	{ //all of my SQL stuff will be within this try block
		
		//Initialize an empty ResultSet object that will store the results of our SQL query
		ResultSet rs = null;
		
		//write the query that we want to send to the database, and assign it to a String
		String sql ="select * from ers_users  where user_name = ?;";			;
		PreparedStatement pst=conn.prepareStatement(sql);
		
		//Put the SQL query into a Statement object (The Connection object has a method for this!!)
		pst.setString(1, uname);
		
		//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
		//The Statement object has a method that takes Strings to execute as a SQL query
rs=pst.executeQuery();
User user=null;
	if(rs.next())
			{
			int ud=rs.getInt("user_id");
				int rd=rs.getInt("role_id");
					Role ro=Role.EMPLOYEE;
					if(rd==2)
						ro=Role.FINANCE_MANAGER;
					String	unm=null;
					//try {
					unm=rs.getString("user_name");
					//System.out.println("user name " + unm);
					//}catch(Exception e) {System.out.println(e.getMessage());};
				
				/*String	email=rs.getString("email");
				String	fname=rs.getString("fname");
				String	lname=rs.getString("lname");*/
				String	pwd=rs.getString("user_password");
					 user=new User(ud,unm,pwd,ro);//fname,lname,email,ro);
					
			}
	if(user!=null)
	return   user;// Optional.of(user);
	else return null;
			}catch(SQLException e) {System.out.println("error !!?");e.printStackTrace();};
		
		
		
	
	
	
	return null;}
	public List<User> getUsers(){ //This will use SQL SELECT functionality

		
		try(Connection conn = ConnectionFactory.getConnection()){ //all of my SQL stuff will be within this try block
			
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_users ;";
			
			//Put the SQL query into a Statement object (The Connection object has a method for this!!)
			Statement statement = conn.createStatement();
			
			//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
			//The Statement object has a method that takes Strings to execute as a SQL query
			rs = statement.executeQuery(sql);
			
			//All the code above makes a call to your database... Now we need to store the data in an ArrayList.
			
			//create an empty ArrayList to be filled with the data from the database
			List<User> employeeList = new ArrayList<>();
			
			//while there are results in the resultset...
			while(rs.next()) {
				int r=rs.getInt("role_id");
				
				Role o=Role.EMPLOYEE;
				if(r==2)
					o=Role.FINANCE_MANAGER;
				
				//Use the all args constructor to create a new Employee object from each returned row from the DB
				User e = new User(	rs.getInt("user_id"),
						//we want to use rs.get for each column in the record
						
						rs.getString("user_name"),
						rs.getString("user_password"),
						rs.getString("fname"),
						rs.getString("lname"),
						rs.getString("email"),
											
						o
						);
				
				//and populate the ArrayList with each new Employee object
				employeeList.add(e); //e is the new Employee object we created above
		
			}
						
			//when there are no more results in rs, the while loop will break
			//then, return the populated ArrayList of Employees
			return employeeList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting employees!");
			e.printStackTrace();
		}
		
		return null; //we add this after the try/catch block, so Java won't yell
		//(Since there's no guarantee that the try will run)
		
	}
public Optional<User> create(  User newUser) 
{ //This is INSERT functionality 
		
		try(Connection conn = ConnectionFactory.getConnection())
		{
			
			//we'll create a SQL statement using parameters to insert a new Employee
			String sql = "INSERT INTO ers_users ( user_name,user_password,fname, lname,email, role_id) " //creating a line break for readability
					    + "VALUES (?, ?, ?,?,?,?); "; //these are parameters!! We have to specify the value of each "?"
			
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); //we use PreparedStatements for SQL commands with variables
			
			//use the PreparedStatement objects' methods to insert values into the query's ?s
			//the values will come from the Employee object we send in.
			ps.setString(1, newUser.getUsername()); //1 is the first ?, 2 is the second, etc.
			ps.setString(2, newUser.getPassword());
			
			ps.setString(3, newUser.getFname()); //1 is the first ?, 2 is the second, etc.
			ps.setString(4, newUser.getLname());
			ps.setString(5, newUser.getEmail());
			if (newUser.getRole().toString().equalsIgnoreCase("EMPLOYEE")) {
				ps.setInt(6, 1);
				
			}
			else {ps.setInt(6, 2);}
			
			//this executeUpdate() method actually sends and executes the SQL command we built
			ps.executeUpdate(); //we use executeUpdate() for inserts, updates, and deletes. 
			//we use executeQuery() for selects
			
			//send confirmation to the console if successul.
			//System.out.println("Employee " + newUser.getUsername()+ " created. Welcome aboard!");
			 try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                int id=generatedKeys.getInt(1);
		                
		             Optional<   User> usr= getUserById(id);
		                System.out.println("Employee " + usr.get().getUsername()+ " created. Welcome aboard!");
		                return usr;
		            }
			 }catch(Exception e) {
		                //throw new SQLException("Creating user failed, no ID obtained.");
		            }
		} catch(SQLException e) {
			System.out.println("Add employee failed! :(");
			e.printStackTrace();
		}
	return Optional.empty();	
}

/*public User login(String unm, String pwd) {
	
	try(Connection conn = ConnectionFactory.getConnection())
	{ //all of my SQL stuff will be within this try block
		
		//Initialize an empty ResultSet object that will store the results of our SQL query
		ResultSet rs = null;
		
		//write the query that we want to send to the database, and assign it to a String
		String sql ="select * from ers_users  where user_name = ?  and  user_password=?;"
				;// "SELECT user_name FROM ers_users  where user_id=?;";
		PreparedStatement pst=conn.prepareStatement(sql);
		
		//Put the SQL query into a Statement object (The Connection object has a method for this!!)
		pst.setString(1, unm);
		pst.setString(2, pwd);
		//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
		//The Statement object has a method that takes Strings to execute as a SQL query
rs=pst.executeQuery();
User user=null;
	while(rs.next())
			{
			int ud=rs.getInt("user_id");
				int rd=rs.getInt("role_id");
					Role ro=Role.EMPLOYEE;
					if(rd==2)
						ro=Role.FINANCE_MANAGER;
					String	um=null;
					try {
					um=rs.getString("user_name");
					System.out.println("user name " + unm);
					}catch(Exception e) {System.out.println(e.getMessage());};
				
				String	email=rs.getString("email");
				String	fname=rs.getString("fname");
				String	lname=rs.getString("lname");
				String	wd=rs.getString("user_password");
					user=new User(ud,um,wd,fname,lname,email,ro);
					
				return user;	
			}
	System.out.println("no such user try again");
	return  null;
			}catch(SQLException e) {System.out.println("error !!?");e.printStackTrace();};
		
		
		
	
	
	
	// TODO Auto-generated method stub
	return null;
}

public Optional<User> register(String unm, String pwd, String fnm, String lnm, String email, int role) {
	// TODO Auto-generated method stub
	try(Connection conn = ConnectionFactory.getConnection())
	{ //all of my SQL stuff will be within this try block
		
		//Initialize an empty ResultSet object that will store the results of our SQL query
		ResultSet rs = null;
		
		//write the query that we want to send to the database, and assign it to a String
		String sql ="INSERT INTO  ers_users( user_name,user_password,fname,lname,email,role_id)" +
		    " VALUES(?,?,?,?,?,?);"
				;// "SELECT user_name FROM ers_users  where user_id=?;";
		PreparedStatement pst=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		//Put the SQL query into a Statement object (The Connection object has a method for this!!)
		pst.setString(1, unm);
		pst.setString(2, pwd);
		pst.setString(3, fnm);
		pst.setString(4, lnm);
		pst.setString(5, email);
		pst.setInt(6, role);
		//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
		//The Statement object has a method that takes Strings to execute as a SQL query
        pst.executeUpdate();
System.out.println("registered successfully ! login please");
try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
    if (generatedKeys.next()) {
        int id=generatedKeys.getInt(1);
        
      Optional<  User> usr= getUserById(id);
        return usr;
    }
}catch(Exception e) {
        //throw new SQLException("Creating user failed, no ID obtained.");
    }
	System.out.println("no such user try again");
	return  null;
			}catch(SQLException e) {System.out.println("error !!?");e.printStackTrace();};
		
		
		
	
	
	
	// TODO Auto-generated method stub
	return null;

	
}*/

public boolean deleteUser(int userId) {
	try(Connection conn = ConnectionFactory.getConnection())
	{ //all of my SQL stuff will be within this try block
		
		//Initialize an empty ResultSet object that will store the results of our SQL query
		ResultSet rs = null;
		
		//write the query that we want to send to the database, and assign it to a String
		String sql ="delete  from ers_users  where user_id = ?;"
				;// "SELECT user_name FROM ers_users  where user_id=?;";
		PreparedStatement pst=conn.prepareStatement(sql);
		
		//Put the SQL query into a Statement object (The Connection object has a method for this!!)
		pst.setInt(1, userId);
		//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
		//The Statement object has a method that takes Strings to execute as a SQL query
		pst.executeUpdate();

	
	return  true;
			}catch(SQLException e) {System.out.println("error !!?");e.printStackTrace();};
		
		
	return false;
}


}

