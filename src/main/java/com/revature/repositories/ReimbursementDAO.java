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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
	public Optional<Reimbursement> create(double amount, Date creation, String description,int author,
			 int status,  int r_type)
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
    		ps.setInt(5, status);
    		ps.setInt(6,r_type);
    		
    		ps.executeUpdate();
    		
    		
    		
    		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int id=generatedKeys.getInt(1);
	               Optional<Reimbursement> reim=getById(id);
	                //System.out.println("Employee " + usr.getUsername()+ " created. Welcome aboard!");
	                return reim;
	            }
    		}catch(SQLException e) {};
    		
		}catch( SQLException e)
		{ System.out.println("insert failed"); 
		e.printStackTrace();};
		
		return null;
		
		
		
	}
    public Optional<Reimbursement> getById(int id) {
    	
    	try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers_reimbersements where reimb_id=?;";
    		PreparedStatement ps=conn.prepareStatement(sql);//,Statement.RETURN_GENERATED_KEYS);
    		ps.setInt(1,id);
    	ResultSet rs=	ps.executeQuery();
    		Reimbursement reimb=null;
    		UserService usrv=new UserService();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		while(rs.next())
    		{
    			//int id, Status status, User author, User resolver, double amount
    			int reid=rs.getInt("reimb_id");
    			double ramount=rs.getInt("reimb_amount");
    			
    			//Date submitted=rs.getDate("submitted");
    			//Date resolved=rs.getDate("resolved");
    			//String description=rs.getString("description");
    			User rauthor= usrv.getUserById(  rs.getInt("author")).get();
    			 User resolver=  usrv.getUserById(rs.getInt(  "resolver")).get();
    			 int rstatus=   rs.getInt("reimb_status");
    			 Status r_status=Status.PENDING;
    			 switch(rstatus)
    			 {
    			 case 2: r_status=   Status.APPROVED;  break;
    			 case 3:  r_status=Status.DENIED; break;
    			 
    			    			 
    			 }
    			 
    			 int reimb_type=   rs.getInt("reimb_type");
    			 ReimbType retype=ReimbType.TRAVEL;
    			 if(reimb_type==2) retype=ReimbType.CERTIFICATION;
    			User oth=rauthor;
    			User re=resolver;
    			 reimb=new Reimbursement(reid, r_status,oth,re,ramount);
    			 return Optional.of(reimb);
    		}
    		
    		
    	}catch(SQLException  e) { e.printStackTrace();};
    	
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
    	
    	try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers_reimbersements where reimb_status=?;";
    		PreparedStatement ps=conn.prepareStatement(sql);//,Statement.RETURN_GENERATED_KEYS);
    		int sta_id=1;
    		if(status.toString().toLowerCase().equals("approved")) sta_id= 2  ;
    		if(status.toString().toLowerCase().equals("denied")) sta_id=  3 ;
    		ps.setInt(1,sta_id);
    	ResultSet rs=	ps.executeQuery();
    		Reimbursement reimb=null;
    		List<Reimbursement> reimbList=new ArrayList<Reimbursement>();
    		UserService usrv=new UserService();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		while(rs.next())
    		{
    			//int id, Status status, User author, User resolver, double amount
    			int reid=rs.getInt("reimb_id");
    			double ramount=rs.getInt("reimb_amount");
    			
    			
    		User rauthor= usrv.getUserById(  rs.getInt("author")).get();
    			 User resolver=  usrv.getUserById(rs.getInt(  "resolver")).get();
    			 int rstatus=   rs.getInt("reimb_status");
    			 Status r_status=Status.PENDING;
    			 switch(rstatus)
    			 {
    			 case 2: r_status=   Status.APPROVED;  break;
    			 case 3:  r_status=Status.DENIED; break;
    			 
    			    			 
    			 }
    			 
    			 int reimb_type=   rs.getInt("reimb_type");
    			 ReimbType retype=ReimbType.TRAVEL;
    			 if(reimb_type==2) retype=ReimbType.CERTIFICATION;
    			 User oth=rauthor;
     			User re=resolver;
    			 reimb=new Reimbursement(reid, r_status,oth,re,ramount);
    			 reimbList.add(reimb);
    		}
    		
    		return   reimbList;
    	}catch(SQLException  e) { e.printStackTrace();};
    	
        return null;
    	
    	
    	
        
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Optional<Reimbursement> update(Reimbursement unprocessedReimbursement) {
    	
    	
    	try(Connection conn=ConnectionFactory.getConnection()) {
    		
    		String sql="update  ers_reimbersements set reimb_status=?, resolver=?, resolved=?  where reimb_id=?;";
    		
    		PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		int id=unprocessedReimbursement.getId();
    		Status st=unprocessedReimbursement.getStatus();
    		
    		
                       if (st.toString().toUpperCase().equals("PENDING")) 
                       {
                    	   ps.setInt(1,2);
			}
                      
    		
    		ps.setInt(2,3);
    		ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
    		ps.setInt(4,id);
    		ps.executeUpdate();
    		
    		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int reimid=generatedKeys.getInt(1);
	               Optional<Reimbursement> reim=getById(reimid);
	                //System.out.println("Employee " + usr.getUsername()+ " created. Welcome aboard!");
	                return reim;
	            }
    		}catch(SQLException e) {};
    		
    		
    		
    	}catch(SQLException e) { e.printStackTrace();}
    	return null;
    }
	public boolean deleteReimbursement(int parseInt) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<Reimbursement> getReimbursements() {
		try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers_reimbersements;";
    		Statement ps=conn.createStatement();
    		
    	ResultSet rs=	ps.executeQuery(sql);
    		Reimbursement reimb=null;
    		UserService usrv=new UserService();
    		
    		List<Reimbursement> remList=new ArrayList<>();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		while(rs.next())
    		{
    			//int id, Status status, User author, User resolver, double amount
    			int reid=rs.getInt("reimb_id");
    			double ramount=rs.getInt("reimb_amount");
    			
    			//Date submitted=rs.getDate("submitted");
    			//Date resolved=rs.getDate("resolved");
    			//String description=rs.getString("description");
    		User	 rauthor= usrv.getUserById(  rs.getInt("author")).get();
    			 User resolver=  usrv.getUserById(rs.getInt(  "resolver")).get();
    			 int rstatus=   rs.getInt("reimb_status");
    			 Status r_status=Status.PENDING;
    			 switch(rstatus)
    			 {
    			 case 2: r_status=   Status.APPROVED;  break;
    			 case 3:  r_status=Status.DENIED; break;
    			 
    			    			 
    			 }
    			 
    			 int reimb_type=   rs.getInt("reimb_type");
    			 ReimbType retype=ReimbType.TRAVEL;
    			 if(reimb_type==2) retype=ReimbType.CERTIFICATION;
    			 User oth=rauthor;
     			User re=resolver
     					;
    			
    			 reimb=new Reimbursement(reid, r_status,oth,re,ramount);
    			 remList.add(reimb);
    			
    		}
    		return remList;
    		
    	}catch(SQLException  e) { e.printStackTrace();};
    	
       return null;
	}
}
