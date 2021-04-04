package ru.rsreu.Chistyakov0518.credits;

/**
 * Abstract class providing features of mortgage loans
 * 
 * @author Chistyakov Pavel
 *
 */
public abstract class MortgageCredit extends Credit {
	private final double insuranceFee;

	public MortgageCredit(double loanSum, int loanDuration, double creditRate,
			LimitingLoanParametersOfferedBank limitParameters, double insuranceFee) {
		super(loanSum, loanDuration);
		this.setLimitParameters(limitParameters);
		this.setLoanRate(creditRate);
		this.insuranceFee = insuranceFee;
	}

	public double getInsuranceFee() {
		return insuranceFee;
	}
}
