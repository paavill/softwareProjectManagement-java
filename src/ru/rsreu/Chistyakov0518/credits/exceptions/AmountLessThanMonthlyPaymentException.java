package ru.rsreu.Chistyakov0518.credits.exceptions;

/**
 * Exception class, initialized in situations where payment amount is less than
 * monthly payment
 * 
 * @author Chistyakov Pavel
 *
 */
public class AmountLessThanMonthlyPaymentException extends Exception {

	private static final long serialVersionUID = -6847442575972977451L;

	public AmountLessThanMonthlyPaymentException(String message) {
		super(message);
	}

}
