package com.revature.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.revature.models.DateExample;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public interface IReimbursementDAO {
	
	Optional<Reimbursement> process(Reimbursement reimb,Status status, User user
			);

	/**
	 * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
	 */
	Optional<Reimbursement> create(double amount, LocalDateTime creation, String description, int author, int status,
			int r_type);

	Optional<Reimbursement> getById(int id);

	/**
	 * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
	 */
	

	/**
	 * <ul>
	 *     <li>Should UpLocalDateTime an existing Reimbursement record in the DB with the provided information.</li>
	 *     <li>Should throw an exception if the upLocalDateTime is unsuccessful.</li>
	 *     <li>Should return a Reimbursement object with upLocalDateTimed information.</li>
	 * </ul>
	 */
	Optional<Reimbursement> update(Reimbursement unprocessedReimbursement);

	boolean deleteReimbursement(int parseInt);

	List<Reimbursement> getReimbursements();

	DateExample getByDateId(String e_id);

	List<Reimbursement> getBystatus(Status status);

	/**
	 * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
	 */
	

}