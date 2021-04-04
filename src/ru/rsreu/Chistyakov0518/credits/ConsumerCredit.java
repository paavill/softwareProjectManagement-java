package ru.rsreu.Chistyakov0518.credits;

public abstract class ConsumerCredit extends Credit {

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
