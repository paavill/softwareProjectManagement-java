package ru.rsreu.Chistyakov0518.credits;

import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public abstract class Credit {
	private double loanSum;
	private int loanDuration;
	private double loanRate;
	private int paymentsNumber;
	private double monthlyPayment;
	private PaymentInformation[] paymentHistory;
	private static final int YEAR_TO_MONTHS_COEFFICIENT = 12;

	public Credit(double loanSum, int loanDuration) {
		this.loanSum = loanSum;
		this.loanDuration = loanDuration;
		this.paymentHistory = new PaymentInformation[loanDuration];
	}

	public abstract double calculateMonthPaymentAmount();

	public abstract void toPayMonthlyAmount() throws LoanRepaymentExpirationException;

	public abstract void toPayAmountMoreMonthSum(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException;

	protected double calculatePercentPartMonthlyPayment() {
		return this.getLoanSum() * this.getLoanRate() / Credit.getYearToMonthsCoefficient();
	}

	protected double calculateLoanRemainingSum(double paymentSum) {
		double result = this.getLoanSum()
				- (paymentSum - this.getLoanSum() * this.getLoanRate() / Credit.getYearToMonthsCoefficient());
		return result;
	}

	protected void checkLoanRepaymentPeriod() throws LoanRepaymentExpirationException {
		if (this.getPaymentsNumber() >= this.getLoanDuration()) {
			throw new LoanRepaymentExpirationException("duration is over");
		}
	}

	protected PaymentInformation paySum(double paymentSum) {
		PaymentInformation information;
		this.setPaymentsNumber(this.getPaymentsNumber() + 1);
		double percentPart = this.calculatePercentPartMonthlyPayment();
		double paymentBody = paymentSum - percentPart;
		if (paymentSum < this.getLoanSum()) {
			this.setLoanSum(this.calculateLoanRemainingSum(paymentSum));
			information = new PaymentInformation(this.getLoanSum(), this.getPaymentsNumber(), this.getLoanRate(),
					paymentSum, percentPart, paymentBody);
		} else {
			double paymentRemainderDifference = paymentSum - this.getLoanSum();
			paymentBody -= paymentRemainderDifference;
			this.setLoanSum(0);
			information = new PaymentInformation(this.getLoanSum(), this.getPaymentsNumber(), this.getLoanRate(),
					paymentBody + percentPart, percentPart, paymentBody);
		}
		return information;
	}

	protected void setPaymentInformation(int paymentNumber, PaymentInformation information) {
		this.paymentHistory[paymentNumber] = information;
	}

	public double getLoanSum() {
		return loanSum;
	}

	protected void setLoanSum(double loan) {
		this.loanSum = loan;
	}

	public int getLoanDuration() {
		return loanDuration;
	}

	protected void setLoanDuration(int duration) {
		this.loanDuration = duration;
	}

	public double getLoanRate() {
		return loanRate;
	}

	protected void setLoanRate(double loanRate) {
		this.loanRate = loanRate;
	}

	public final StringBuilder getPaymentHistory() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < this.getPaymentsNumber(); i++) {
			result.append(paymentHistory[i].toFormatString()).append("\n");
		}
		return result;
	}

	public final double getAllPaidAmount() {
		float result = 0;
		for (PaymentInformation i : paymentHistory) {
			result += i.getPaymentAmount();
		}
		return result;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	protected void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	protected static int getYearToMonthsCoefficient() {
		return YEAR_TO_MONTHS_COEFFICIENT;
	}

	public int getPaymentsNumber() {
		return paymentsNumber;
	}

	protected void setPaymentsNumber(int paymentsNumber) {
		this.paymentsNumber = paymentsNumber;
	}
}
