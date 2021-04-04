package ru.rsreu.Chistyakov0518.credits;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public abstract class Credit implements Comparable<Credit> {

	public static final Credit NULL_CREDIT = new Credit() {
		@Override
		public String getBankName() {
			return "";
		}

		@Override
		public String getCreditType() {
			return "";
		}

		@Override
		public int compareTo(Credit o) {
			return 0;
		}

	};
	private static final int YEAR_TO_MONTHS_COEFFICIENT = 12;
	private boolean isOpen = true;
	private double loanSum;
	private int loanDuration;
	private double loanRate;
	private int paymentsNumber;
	private double monthlyPayment;
	private PaymentInformation[] paymentHistory;
	private LimitingLoanParametersOfferedBank limitParameters;

	private Credit() {

	}

	public Credit(double loanSum, int loanDuration) {
		this.loanSum = loanSum;
		this.loanDuration = loanDuration;
		this.paymentHistory = new PaymentInformation[loanDuration];
		this.paymentsNumber = 0;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	protected void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	protected void setClose() {
		this.isOpen = false;
	}

	public boolean getState() {
		return this.isOpen;
	}

	public int getPaymentsNumber() {
		return paymentsNumber;
	}

	protected void setPaymentsNumber(int paymentsNumber) {
		this.paymentsNumber = paymentsNumber;
	}

	protected void setPaymentInformation(int paymentNumber, PaymentInformation information) {
		this.paymentHistory[paymentNumber] = information;
	}

	public double getLoanSum() {
		return loanSum;
	}

	protected void setLoanSum(double loanSum) {
		this.loanSum = loanSum;
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

	protected LimitingLoanParametersOfferedBank getLimitParameters() {
		return limitParameters;
	}

	protected void setLimitParameters(LimitingLoanParametersOfferedBank limitParameters) {
		this.limitParameters = limitParameters;
	}

	public double calculateMonthPaymentAmount() {
		double monthLoanRate = this.getLoanRate() / Credit.YEAR_TO_MONTHS_COEFFICIENT;
		double result = (this.getLoanSum()
				* (monthLoanRate + (double) monthLoanRate / (Math.pow(1 + monthLoanRate, this.getLoanDuration()) - 1)));
		return result;
	}

	public abstract String getBankName();

	public abstract String getCreditType();

	protected boolean checkLoanDurationInRange(int duration) {
		return this.getLimitParameters().getMinLoanDuration() <= duration
				&& duration <= this.getLimitParameters().getMaxLoanDuration();
	}

	protected boolean checkLoanAmountInRange(double loanSum) {
		return this.getLimitParameters().getMinLoanSum() <= loanSum
				&& loanSum <= this.getLimitParameters().getMaxLoanSum();
	}

	public void toPayMonthlyAmount(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException {
		if (this.getState()) {
			if (sumToPay >= this.getMonthlyPayment()) {
				this.checkLoanRepaymentPeriod();
				PaymentInformation information = this.paySum(sumToPay);
				this.setPaymentInformation(this.getPaymentsNumber() - 1, information);
			} else {
				throw new AmountLessThanMonthlyPaymentException(
						Resourcer.getString("messages.exceptions.loanRepaymentExceptionSum"));
			}
		}
	}

	protected double calculatePercentPartMonthlyPayment() {
		return this.loanSum * this.loanRate / Credit.YEAR_TO_MONTHS_COEFFICIENT;
	}

	protected double calculateLoanRemainingSum(double paymentSum) {
		return this.loanSum - (paymentSum - this.loanSum * this.loanRate / Credit.YEAR_TO_MONTHS_COEFFICIENT);
	}

	protected void checkLoanRepaymentPeriod() throws LoanRepaymentExpirationException {
		if (this.getPaymentsNumber() >= this.loanDuration) {
			throw new LoanRepaymentExpirationException(
					Resourcer.getString("messages.exceptions.amountLessMonthlyPayment"));
		}
	}

	protected PaymentInformation paySum(double paymentSum) {
		PaymentInformation information;
		this.paymentsNumber += 1;
		double percentPart = this.calculatePercentPartMonthlyPayment();
		double paymentBody = paymentSum - percentPart;
		if (paymentSum < this.loanSum) {
			this.loanSum = this.calculateLoanRemainingSum(paymentSum);
			information = new PaymentInformation(this.loanSum, this.paymentsNumber, this.loanRate, paymentSum,
					percentPart, paymentBody);
		} else {
			this.setClose();
			double paymentRemainderDifference = paymentSum - this.loanSum;
			paymentBody -= paymentRemainderDifference;
			this.loanSum = 0;
			information = new PaymentInformation(this.loanSum, this.paymentsNumber, this.loanRate,
					paymentBody + percentPart, percentPart, paymentBody);
		}
		return information;
	}

	@Override
	public int compareTo(Credit o) {
		int result = ((Double) this.loanRate).compareTo(o.loanRate);
		return result;
	}

	public final PaymentInformation[] getPaymentHistory() {
		return this.paymentHistory;
	}

	public final double getAllPaidAmount() {
		float result = 0;
		for (int i = 0; i < this.getPaymentsNumber(); i++) {
			result += paymentHistory[i].getPaymentAmount();
		}
		return result;
	}
}
