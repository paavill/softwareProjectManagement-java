package ru.rsreu.Chistyakov0318;

/**
 * Error handling class due to misplaced negative numbers
 * 
 * @author Chistyakov Pavel
 *
 */
public class InvalidNegativeIndexNumbException extends Exception {

	private static final long serialVersionUID = -3972098553789660806L;

	public InvalidNegativeIndexNumbException(String message) {
		super(message);
	}
}
