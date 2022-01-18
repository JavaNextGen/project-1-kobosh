package com.revature.models;

public class ReimbAppDTO {
	private  String username;
	private  String password;
	private  String description;
	private  double amount;
	private  ReimbType reimb_type;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public ReimbType getReimb_type() {
		return reimb_type;
	}
	public void setReimb_type(ReimbType reimb_type) {
		this.reimb_type = reimb_type;
	}

}
