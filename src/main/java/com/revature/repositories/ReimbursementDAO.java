package com.revature.repositories;

import com.revature.models.DateExample;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.IUserService;
import com.revature.util.ConnectionFactory;

import models.UserService;

import java.awt.Image;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import java.time.LocalTime;
//import java.time.LocalLocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
//import java.util.LocalTime;
//import java.sql.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class ReimbursementDAO implements IReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
	
	@Override
	public Optional<Reimbursement> create(double amount, 
			LocalDateTime creation, String description,int author,
			 int status,  int r_type)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="INSERT INTO ers_reimbursements (reimb_amount,submitted, description,author,"
    				+ "reimb_status, reimb_type)"
    				+ " VALUES(?,?,?,?,?,?);";
    		PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		ps.setDouble(1,amount);
    		//ps.setTime(2, submitted);
    		//ps.setLocalTime(2, (submitted.getTime()));
    		ps.setObject(2,  LocalDateTime.now());//System.currentTimeMillis()));

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
	                return  reim;//  Optional.ofNullable( reim);
	            }
    		}catch(SQLException e) {};
    		
		}catch( SQLException e)
		{ System.out.println("insert failed"); 
		e.printStackTrace();};
		
		return  Optional.ofNullable( null);
		
		
		
	}
    @Override
	public Optional<Reimbursement> getById(int id) {
    	
    	try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers_reimbursements where reimb_id=?;";
    		PreparedStatement ps=conn.prepareStatement(sql);//,Statement.RETURN_GENERATED_KEYS);
    		ps.setInt(1,id);
    	ResultSet rs=	ps.executeQuery();
    		Reimbursement reimb=null;
    		IUserService usrv=new UserService();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		while(rs.next())
    		{
    			//int id, Status status, User author, User resolver, double amount
    			int reid=rs.getInt("reimb_id");
    			double ramount=rs.getInt("reimb_amount");
    			int res=rs.getInt(  "resolver");
    			 User resolver=null;
    			//LocalTime submitted=rs.getLocalTime("submitted");
    			//LocalTime resolved=rs.getLocalTime("resolved");
    			String description=rs.getString("description");
    			User rauthor= usrv.getUserById(  rs.getInt("author")).get();
    			if(res>0)
    			{ resolver=  usrv.getUserById(res).get(); }
    			 int rstatus=   rs.getInt("reimb_status");
    			 Status r_status=Status.values()[--rstatus];//PENDING;
    			 
    			 
    			 int reimb_type=   rs.getInt("reimb_type");
    			 ReimbType retype=ReimbType.values()[--reimb_type];//.TRAVEL;
    			// if(reimb_type==2) retype=ReimbType.CERTIFICATION;
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
   
    @Override
	public List<Reimbursement> getBystatus(Status status) {
    	
    	try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers_reimbursements where reimb_status=?;";
    		PreparedStatement ps=conn.prepareStatement(sql);//,Statement.RETURN_GENERATED_KEYS);
    		int sta_id= status.ordinal()+1;
    	
    		ps.setInt(1,sta_id);
    	ResultSet rs=	ps.executeQuery();
    		Reimbursement reimb=null;
    		List<Reimbursement> reimbList=new ArrayList<Reimbursement>();
    		IUserService usrv=new UserService();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		while(rs.next())
    		{
    			reimb = getReimbursement(rs, usrv);
   			 		 reimbList.add(reimb);
    		}
    		
    		return   reimbList;
    	}catch(SQLException  e) { e.printStackTrace();};
    	
        return null;
    	
    	
    	
        
    }

    /**
     * <ul>
     *     <li>Should UpLocalTime an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the upLocalTime is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with upLocalTimed information.</li>
     * </ul>
     */
    @Override
	public Optional<Reimbursement> update(Reimbursement unprocessedReimbursement) {
    	System.out.println("calling  upLocalTime method in  DAO");
    	
    	try(Connection conn=ConnectionFactory.getConnection()) {
    		
    		String sql="update  ers_reimbursements set reimb_status=?,"
    				+ " resolver=?, resolved=?  where reimb_id=?;";
    		
    		PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		int id=unprocessedReimbursement.getId();
    		Status st=unprocessedReimbursement.getStatus();
    		
    		
                       if (st.ordinal()==0)
                       {
                    	   ps.setInt(1,2); //approved
                       }
                       else {  return Optional.empty(); /*ps.setInt(1,st.ordinal());*/  }
    		
                         ps.setInt(2,3);
    		ps.setObject(3, LocalDateTime.now());
    		ps.setInt(4,id);
    		ps.executeUpdate();
    		
    		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int reimid=generatedKeys.getInt(1);
	               Optional<Reimbursement> reim=getById(reimid);
	                System.out.println("Reimb " + reim.get()+ " upLocalTimed!");
	                return reim;
	            }
    		}catch(SQLException e) {};
    		
    		
    		
    	}catch(SQLException e) { e.printStackTrace();}
    	return Optional.empty();
    }
	@Override
	public boolean deleteReimbursement(int parseInt) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Reimbursement> getReimbursements() {
		try(Connection conn = ConnectionFactory.getConnection())
		{
    		String sql="select * from ers_reimbursements;";
    		Statement ps=conn.createStatement();
    		
    	ResultSet rs=	ps.executeQuery(sql);
    		Reimbursement reimb=null;
    		IUserService usrv=new UserService();
    		
    		List<Reimbursement> remList=new ArrayList<>();
    		//reimb_id ,amount, submitted,resolved,description,author,receipt ,resolver,status,reimb_type
    		while(rs.next())
    		{
    			reimb = getReimbursement(rs, usrv);
    			 remList.add(reimb);
    			
    		}
    		return remList;
    		
    	}catch(SQLException  e) { e.printStackTrace();};
    	
       return null;
	}
	private Reimbursement getReimbursement(ResultSet rs, IUserService usrv) throws SQLException {
		Reimbursement reimb;
		//int id, Status status, User author, User resolver, double amount
		int reid=rs.getInt("reimb_id");
		double ramount=rs.getInt("reimb_amount");
		
		LocalDateTime submitted=rs.getObject("submitted",LocalDateTime.class);
		System.out.println(submitted);		
				//.getLocalTime("submitted").;
		LocalDateTime resolved=rs.getObject("resolved",LocalDateTime.class);
		String description=rs.getString("description");
 		User	 rauthor= usrv.getUserById(  rs.getInt("author")).get();
		int res=rs.getInt(  "resolver");
		
		 Optional<User> resolver=  usrv.getUserById(res);
		 int rstatus=   rs.getInt("reimb_status");
		 Status r_status=Status.values()[--rstatus];//.PENDING;
		/* switch(rstatus)
		 {
		 case 2: r_status=   Status.APPROVED;  break;
		 case 3:  r_status=Status.DENIED; break;
		 
		    			 
		 }*/
		 
		 int reimb_type= rs.getInt("reimb_type");
		 ReimbType retype=ReimbType.values()[--reimb_type];//.TRAVEL;
		 //if(reimb_type==2) retype=ReimbType.CERTIFICATION;
		 User oth=rauthor;
		 User re= null;
		 if(resolver.isPresent())
		 {     re=resolver.get();      }
			Image im=null;	
		/*
		 int id, Status status, User author, User resolver,
 		double amount,ReimbType reimb_type ,String description,
 		LocalTime creation,LocalTime resolution, Image receipt
		 */
			
		 reimb=new Reimbursement(reid, r_status,oth,re, ramount,
				 retype,
				 description,
				 submitted,
				 resolved,null);
		return reimb;
	}
	
	@Override
	public Optional<Reimbursement> process(Reimbursement reimb, Status status, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DateExample getByDateId(String e_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
