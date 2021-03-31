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

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public StringBuilder toFormatString() {
		StringBuilder result = new StringBuilder();
		result.append(String.format("%3d ", this.loanDuration)).append(String.format("%10.2f ", this.loanSum))
				.append(String.format("%4.2f ", this.loanRate)).append(String.format("%10.2f ", this.paymentAmount))
				.append(String.format("%10.2f ", this.percentPartPaymentAmount))
				.append(String.format("%10.2f ", this.loanPartPaymentAmount));
		return result;
	}
}
