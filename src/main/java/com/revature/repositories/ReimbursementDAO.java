package com.revature.repositories;

import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

import java.awt.Image;
import java.security.Timestamp;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
	public Reimbursement create(double amount, Date creation, String description,int author,
			 Status status,  ReimbType r_type)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="INSERT INTO ers_reimbersements (reimb_amount,submitted, description,author,"
    				+ "reimb_status, reimb_type)"
    				+ " VALUES(?,?,?,?,?,?);";
    		PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		ps.setDouble(1,amount);
    		//ps.setTime(2, submitted);
    		//ps.setTimestamp(2, (submitted.getTime()));
    		ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));

    		ps.setString(3, description);
    		
    		ps.setInt(4, author);
    		//ps.setBytes(6, null);//(Blob) receipt);
    		//ps.setInt(7, (Integer) null);//resolver);
    		int st= 1;
    		if(status.equals("denied".toUpperCase())) st= 4;
    		if(status.equals("resolved".toUpperCase())) st= 3;
    		if(status.equals("pending".toUpperCase())) st= 2 ;
    		ps.setInt(5,st);
    		int rtype=1;
    		if(r_type.equals("certification".toUpperCase()) )  rtype= 2 ;
    		ps.setInt(6,rtype);
    		
    		ps.executeUpdate();
    		
		}catch( SQLException e)
		{ System.out.println("insert failed"); 
		e.printStackTrace();};
		return null;
		
		
		
		
	}
    public Optional<Reimbursement> getById(int id) {
    	
    	try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers.reimbersements where reim_id=?;";
    		PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		ps.setInt(1,id);
    	ResultSet rs=	ps.executeQuery(sql);
    		Reimbursement reimb=null;
    		UserService usrv=new UserService();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		/*while(rs.next())
    		{
    			//int id, Status status, User author, User resolver, double amount
    			int reid=rs.getInt("reimb_id");
    			double amount=rs.getInt("amount");
    			Date submitted=rs.getDate("submitted");
    			Date resolved=rs.getDate("resolved");
    			//String description=rs.getString("description");
    			// User author= usrv.getUserById(  rs.getInt("author");
    			// User auth=getById(author.getId());
    			// Blob receipt=  null; rs.getBlob(5);
    			 User resolver=  usrv.getUserById(rs.getInt(  "resolver"));
    			 int status=   rs.getInt("status");
    			 int reimb_type=   rs.getInt("reimb_type");
    			
    			//Reimbursement reimb=new Reimbursement(reid,);
    			
    		}*/
    		return null;
    		
    	}catch(SQLException  e) {};
    	
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }
}
