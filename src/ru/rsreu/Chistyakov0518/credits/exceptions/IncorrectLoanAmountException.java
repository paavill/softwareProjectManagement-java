package ru.rsreu.Chistyakov0518.credits.exceptions;

public class IncorrectLoanAmountException extends Exception {

	private static final long serialVersionUID = 5907019841595639865L;

	public IncorrectLoanAmountException(String message) {
		super(message);
	}
}
