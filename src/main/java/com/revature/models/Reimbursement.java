package com.revature.models;

import java.awt.Image;
import java.sql.Date;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 *      <li>Reimb_type ReimbType</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
	private String  description;
	private  Date  creation ;
	private  Date  resolution ;
	private  Image receipt ;
	private  ReimbType reimb_type; ;


//new Reimbursement(2, Status.PENDING, GENERIC_EMPLOYEE_1, null, 150.00);
    public Reimbursement()
    {super();}
    public Reimbursement(int id, Status status, User author,User resolver, double amount) {
        super(id,status,author,resolver,amount);
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver,
    		double amount,ReimbType reimb_type ,String description,
    		Date creation,Date resolution, Image receipt) {
        super(id, status, author, resolver, amount);
        this.creation=creation;
        this.resolution=resolution;
        this.description=description;
        this.receipt=receipt;
        this.reimb_type=reimb_type;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getResolution() {
		return resolution;
	}

	public void setResolution(Date resolution) {
		this.resolution = resolution;
	}

	public Image getReceipt() {
		return receipt;
	}

	public void setReceipt(Image receipt) {
		this.receipt = receipt;
	}
	@Override
	
	public String toString() {
		
		
		return "AbstractReimbursement{" +
                "id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", author=" + super.getAuthor()+
                ", resolver=" + super.getResolver() +
                ", amount=" + super.getAmount() +
                ", description " + this.description +
                ",creation " +this.creation +
                ", resolution " + this.resolution +
                ", receipt " + this.receipt +
                
                '}';
		
		
	}

	public ReimbType getReimb_type() {
		return reimb_type;
	}

	public void setReimb_type(ReimbType reimb_type) {
		this.reimb_type = reimb_type;
	}
}
