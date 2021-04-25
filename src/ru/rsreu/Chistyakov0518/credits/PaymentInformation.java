package ru.rsreu.Chistyakov0518.credits;

/**
 * Class for storing and retrieving payment information
 * 
 * @author Chistyakov Pavel
 *
 */
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

	public final double getLoanSum() {
		return loanSum;
	}

	public final int getPaymentNumber() {
		return loanDuration;
	}

	public final double getLoanRate() {
		return loanRate;
	}

	public final double getPercentPartPaymentAmount() {
		return percentPartPaymentAmount;
	}

	public final double getLoanPartPaymentAmount() {
		return loanPartPaymentAmount;
	}

	public final double getPaymentAmount() {
		return paymentAmount;
	}
}
