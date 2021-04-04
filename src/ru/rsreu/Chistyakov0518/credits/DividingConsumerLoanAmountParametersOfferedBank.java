package ru.rsreu.Chistyakov0518.credits;

/**
 * Interest rate accrual class for consumer loans
 * 
 * @author Chistyakov Pavel
 *
 */
public class DividingConsumerLoanAmountParametersOfferedBank {
	/**
	 * Amount Range Boundary Fields
	 */
	private double lowBordHighLoanSumRange;
	private double lowBordMiddleLoanSumRange;
	/**
	 * Loan rates selected depending on the amount
	 */
	private double highRangeRate;
	private double middleRangeRate;
	private double lowRangeRate;

	public DividingConsumerLoanAmountParametersOfferedBank(double lowBordHighLoanSumRange,
			double lowBordMiddleLoanSumRange, double highRangeRate, double middleRangeRate, double lowRangeRate) {
		super();
		this.lowBordHighLoanSumRange = lowBordHighLoanSumRange;
		this.lowBordMiddleLoanSumRange = lowBordMiddleLoanSumRange;
		this.highRangeRate = highRangeRate;
		this.middleRangeRate = middleRangeRate;
		this.lowRangeRate = lowRangeRate;
	}

	protected double getHighRangeRate() {
		return highRangeRate;
	}

	protected void setHighRangeRate(double highRangeRate) {
		this.highRangeRate = highRangeRate;
	}

	protected double getMiddleRangeRate() {
		return middleRangeRate;
	}

	protected void setMiddleRangeRate(double middleRangeRate) {
		this.middleRangeRate = middleRangeRate;
	}

	protected double getLowRangeRate() {
		return lowRangeRate;
	}

	protected void setLowRangeRate(double lowRangeRate) {
		this.lowRangeRate = lowRangeRate;
	}

	protected double getLowBordHighLoanSumRange() {
		return lowBordHighLoanSumRange;
	}

	protected void setLowBordHighLoanSumRange(double lowBordHighLoanSumRange) {
		this.lowBordHighLoanSumRange = lowBordHighLoanSumRange;
	}

	protected double getLowBordMiddleLoanSumRange() {
		return lowBordMiddleLoanSumRange;
	}

	protected void setLowBordMiddleLoanSumRange(double lowBordMiddleLoanSumRange) {
		this.lowBordMiddleLoanSumRange = lowBordMiddleLoanSumRange;
	}
}
