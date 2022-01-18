package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public interface IReimursementService {

	Optional<Reimbursement> process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver)
			throws Exception;

	/**
	 * Should retrieve all reimbursements with the correct status.
	 */
	List<Reimbursement> getReimbursementsByStatus(Status status);
	
	public Optional<Reimbursement> getById(int id);

}