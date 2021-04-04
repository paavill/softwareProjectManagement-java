package ru.rsreu.Chistyakov0518.credits.exceptions;

/**
 * Exception class that is initialized in situations where the loan has passed
 * the due date, but the client is trying to pay back
 * 
 * @author Chistyakov Pavel
 *
 */
public class LoanRepaymentExpirationException extends Exception {

	private static final long serialVersionUID = 6444298348741670945L;

	public LoanRepaymentExpirationException(String message) {
		super(message);
	}

}
