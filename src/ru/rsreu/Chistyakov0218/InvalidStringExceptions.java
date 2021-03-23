package ru.rsreu.Chistyakov0218;

import com.sun.istack.internal.NotNull;

/**
 * An exception-class for catching errors that occur when an invalid string
 * 
 * @author Chistyakov Pavel
 *
 */
public class InvalidStringExceptions extends Exception {

	private static final long serialVersionUID = 8076855349503656367L;

	@NotNull
	private final StringExceptionCodes excCode;

	public InvalidStringExceptions(StringExceptionCodes exC, String message) {
		super(message);
		this.excCode = exC;
	}

	public StringExceptionCodes getErrorCode() {
		return this.excCode;
	}
}
