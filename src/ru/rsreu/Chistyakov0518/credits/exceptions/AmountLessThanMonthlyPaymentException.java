package ru.rsreu.Chistyakov0518.credits.exceptions;

public class AmountLessThanMonthlyPaymentException extends Exception {

	private static final long serialVersionUID = -6847442575972977451L;

	public AmountLessThanMonthlyPaymentException(String message) {
		super(message);
	}

}
