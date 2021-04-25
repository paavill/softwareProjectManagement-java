package ru.rsreu.Chistyakov0518;

public class Bank {

	private final String bankName;
	private final double loanRate;
	private final int minLoanDuration;
	private final int maxLoanDuration;
	private final double minLoanSum;
	private final double maxLoanSum;

	public Bank(String bankName, double loanRate, int minLoanDuration, int maxLoanDuration, double minLoanSum,
			double maxLoanSum) {
		super();
		this.bankName = bankName;
		this.loanRate = loanRate;
		this.minLoanDuration = minLoanDuration;
		this.maxLoanDuration = maxLoanDuration;
		this.minLoanSum = minLoanSum;
		this.maxLoanSum = maxLoanSum;
	}

	public final String getBankName() {
		return bankName;
	}

	public final int getMinLoanDuration() {
		return minLoanDuration;
	}

	public final int getMaxLoanDuration() {
		return maxLoanDuration;
	}

	public final double getMinLoanSum() {
		return minLoanSum;
	}

	public final double getMaxLoanSum() {
		return maxLoanSum;
	}

	public final double getLoanRate() {
		return loanRate;
	}

}
