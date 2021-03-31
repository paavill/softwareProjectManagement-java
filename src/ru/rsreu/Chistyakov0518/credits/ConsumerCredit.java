package ru.rsreu.Chistyakov0518.credits;

import ru.rsreu.Chistyakov0518.credits.exceptions.IncorrectLoanAmountException;
import ru.rsreu.Chistyakov0518.credits.exceptions.IncorrectLoanDurationException;

public abstract class ConsumerCredit extends Credit {
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
	/**
	 * 
	 */
	private double lowBordHighLoanSumRange;
	private double lowBordMiddleLoanSumRange;

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

	public ConsumerCredit(double loanSum, int loanDuration, double maxLoanSum, double minLoanSum, int maxLoanDuration,
			int minLoanDuration) throws IncorrectLoanDurationException, IncorrectLoanAmountException {
		super(loanSum, loanDuration);
		this.maxLoanSum = maxLoanSum;
		this.minLoanSum = minLoanSum;
		this.maxLoanDuration = maxLoanDuration;
		this.minLoanDuration = minLoanDuration;
		this.checkLoanDurationInRange(loanDuration);
		this.checkLoanAmountInRange(loanSum);
	}

	private void checkLoanDurationInRange(int duration) throws IncorrectLoanDurationException {
		if (this.minLoanDuration > duration || duration > this.maxLoanDuration) {
			throw new IncorrectLoanDurationException("d");
		}
	}

	private void checkLoanAmountInRange(double loanSum) throws IncorrectLoanAmountException {
		if (this.minLoanSum > loanSum || loanSum > this.maxLoanSum) {
			throw new IncorrectLoanAmountException("enc");
		}
	}
}
