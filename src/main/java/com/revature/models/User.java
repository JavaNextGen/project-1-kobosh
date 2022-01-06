package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {
	private String   fname  ;
	private String   lname  ;
	private String    email  ;

    public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User(int id, String username, String password, Role role)
    {
        super(id,username, password, role);
        this.fname=null;
        this.lname=null;
        this.email=null;
    }
    public User(int id, String username, String password, String fnm,String lnm, String eml,Role role)
    {
        super(id,username, password, role);
        this.fname=fnm;
        this.lname=lnm;
        this.email=eml;
    }

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	 @Override
	    public String toString() {
	        return "User{" +
	                "id=" + super.getId() +
	                ", username='" + super.getUsername() + '\'' +
	                ", password='" +super.getPassword() + '\'' +
	                
	                ", f_name "+ this.fname + '\'' +
	                 ", l_name "+ this.lname + '\'' +
	                  ", email "+ this.email + '\'' +
	                  ", role=" + super.getRole() +
	                '}';
	    }
}
