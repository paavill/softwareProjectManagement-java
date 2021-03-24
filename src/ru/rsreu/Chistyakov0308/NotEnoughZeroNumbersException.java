package ru.rsreu.Chistyakov0308;

/**
 * Handles errors related to insufficient number of zero elements
 * 
 * @author Chistyakov Pavel
 *
 */
public class NotEnoughZeroNumbersException extends Exception {

	private static final long serialVersionUID = -1509304218000011707L;

	public NotEnoughZeroNumbersException(String message) {
		super(message);
	}
}
