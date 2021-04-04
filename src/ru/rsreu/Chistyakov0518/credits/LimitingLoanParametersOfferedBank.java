package ru.rsreu.Chistyakov0518.credits;

public class LimitingLoanParametersOfferedBank {
	/**
	 * 
	 */
	private double maxLoanSum;
	private double minLoanSum;
	/**
	 * 
	 */
	private int minLoanDuration;
	private int maxLoanDuration;
	
	public LimitingLoanParametersOfferedBank(double maxLoanSum, double minLoanSum, int minLoanDuration,
			int maxLoanDuration) {
		this.maxLoanSum = maxLoanSum;
		this.minLoanSum = minLoanSum;
		this.minLoanDuration = minLoanDuration;
		this.maxLoanDuration = maxLoanDuration;
	}
	
	protected double getMaxLoanSum() {
		return maxLoanSum;
	}

	protected void setMaxLoanSum(double maxLoanSum) {
		this.maxLoanSum = maxLoanSum;
	}

	protected double getMinLoanSum() {
		return minLoanSum;
	}

	protected void setMinLoanSum(double minLoanSum) {
		this.minLoanSum = minLoanSum;
	}

	protected int getMinLoanDuration() {
		return minLoanDuration;
	}

	protected void setMinLoanDuration(int minLoanDuration) {
		this.minLoanDuration = minLoanDuration;
	}

	protected int getMaxLoanDuration() {
		return maxLoanDuration;
	}

	protected void setMaxLoanDuration(int maxLoanDuration) {
		this.maxLoanDuration = maxLoanDuration;
	}
}
