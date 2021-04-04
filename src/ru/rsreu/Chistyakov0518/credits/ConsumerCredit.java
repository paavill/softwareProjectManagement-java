package ru.rsreu.Chistyakov0518.credits;

/**
 * An abstract class that provides methods for checking the limits imposed on
 * consumer loans by various banks
 * 
 * @author Chistyakov Pavel
 *
 */
public abstract class ConsumerCredit extends Credit {
	/**
	 * The field of restrictions typical of consumer loans of various banks.
	 * Contains division of the range of the amount that the bank can provide into
	 * parts with different interest rates on the loan
	 */
	private DividingConsumerLoanAmountParametersOfferedBank divideParameters;

	public ConsumerCredit(double loanSum, int loanDuration, LimitingLoanParametersOfferedBank limitParameters,
			DividingConsumerLoanAmountParametersOfferedBank divideParameters) {
		super(loanSum, loanDuration);
		this.setLimitParameters(limitParameters);
		if (!this.checkLoanDurationInRange(loanDuration)) {
			this.setLoanDuration(this.getLimitParameters().getMinLoanDuration());
		}
		if (!this.checkLoanAmountInRange(loanSum)) {
			this.setLoanSum(this.getLimitParameters().getMinLoanSum());
		}
		this.checkLoanAmountInRange(loanSum);
		this.divideParameters = divideParameters;
		this.setLoanRate(this.getLoanRateMatchSum(loanSum));
	}

	/**
	 * The method returns the value of the interest rate depending on the range of
	 * amounts (division by ranges in divideParameters) the loan amount is
	 * 
	 * @param loanSum
	 * @return credit rate
	 */
	private double getLoanRateMatchSum(double loanSum) {
		if (this.divideParameters.getLowBordHighLoanSumRange() < loanSum
				&& loanSum <= this.getLimitParameters().getMaxLoanSum()) {
			return divideParameters.getHighRangeRate();
		} else if (this.divideParameters.getLowBordMiddleLoanSumRange() < loanSum
				&& loanSum <= this.divideParameters.getLowBordHighLoanSumRange()) {
			return this.divideParameters.getMiddleRangeRate();
		} else {
			return this.divideParameters.getLowRangeRate();
		}
	}
}
