package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
//import com.revature.services.SQLException;
import com.revature.util.ConnectionFactory;

public class AuthDAO {
	public  boolean login(String nm,String pwd)
	{
try(Connection conn = ConnectionFactory.getConnection()){ //all of my SQL stuff will be within this try block
			
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;			
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_users  where user_name=? and user_password=?;";
			
			//Put the SQL query into a Statement object (The Connection object has a method for this!!)
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		    ps.setString(1, nm);
		    ps.setString(2, pwd);
			//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
			//The Statement object has a method that takes Strings to execute as a SQL query
			rs = ps.executeQuery();
			if(rs.next())
			  return true;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting employees!");
			e.printStackTrace();
		}
		
		return false; //we add this after the try/catch block, so Java won't yell
		//(Since there's no guarantee that the try will run)
		
	}

	/*public Optional<User> register(User user) {
try(Connection conn = ConnectionFactory.getConnection()){ //all of my SQL stuff will be within this try block
			
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;			
			//write the query that we want to send to the database, and assign it to a String
			String sql = "INSERT INTO  ers_users  where user_name=? and user_password=?;";
			
			//Put the SQL query into a Statement object (The Connection object has a method for this!!)
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		    ps.setString(1, nm);
		    ps.setString(2, pwd);
			//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
			//The Statement object has a method that takes Strings to execute as a SQL query
			rs = ps.executeQuery();
			if(rs.next())
			  return true;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting employees!");
			e.printStackTrace();
		}
		
		return false;
	}*/

}
