package ru.rsreu.Chistyakov0518.credits;

public class PaymentInformation {
	private final double loanSum;
	private final int loanDuration;
	private final double loanRate;
	private final double paymentAmount;
	private final double percentPartPaymentAmount;
	private final double loanPartPaymentAmount;

	public PaymentInformation(double loanSum, int loanDuration, double loanRate, double paymentAmount,
			double percentPartPaymentAmount, double loanPartPaymentAmount) {
		this.loanRate = loanRate;
		this.loanSum = loanSum;
		this.loanDuration = loanDuration;
		this.paymentAmount = paymentAmount;
		this.percentPartPaymentAmount = percentPartPaymentAmount;
		this.loanPartPaymentAmount = loanPartPaymentAmount;
	}

	public double getLoanSum() {
		return loanSum;
	}

	public int getPaymentNumber() {
		return loanDuration;
	}

	public double getLoanRate() {
		return loanRate;
	}

	public double getPercentPartPaymentAmount() {
		return percentPartPaymentAmount;
	}

	public double getLoanPartPaymentAmount() {
		return loanPartPaymentAmount;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}
}
