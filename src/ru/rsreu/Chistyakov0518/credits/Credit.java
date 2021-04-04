package ru.rsreu.Chistyakov0518.credits;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.exceptions.*;

/**
 * 
 * @author Chistyakov Pavel
 *
 */
public abstract class Credit implements Comparable<Credit> {
	/**
	 * NullObject pattern implementation
	 */
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

	/**
	 * The field stores the status of the loan, if the loan is open - true, if
	 * closed - false
	 */
	private boolean isOpen = true;

	/**
	 * The amount for which a loan is taken
	 */
	private double loanSum;

	/**
	 * Duration of loan repayment
	 */
	private int loanDuration;

	/**
	 * Interest rate on a loan in the form of a coefficient
	 */
	private double loanRate;

	/**
	 * Current loan payment number
	 */
	private int paymentsNumber;

	/**
	 * Monthly loan payment
	 */
	private double monthlyPayment;

	/**
	 * Data on all loan payments
	 */
	private PaymentInformation[] paymentHistory;

	/**
	 * Limiting parameters specific to all loans
	 */
	private LimitingLoanParametersOfferedBank limitParameters;

	private Credit() {

	}

	/**
	 * 
	 * @param loanSum
	 * @param loanDuration
	 */
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

	public final PaymentInformation[] getPaymentHistory() {
		return this.paymentHistory;
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

	/**
	 * Method calculating monthly loan payment
	 * 
	 * @return monthly loan payment
	 */
	public double calculateMonthPaymentAmount() {
		double monthLoanRate = this.getLoanRate() / Credit.YEAR_TO_MONTHS_COEFFICIENT;
		double result = (this.getLoanSum()
				* (monthLoanRate + (double) monthLoanRate / (Math.pow(1 + monthLoanRate, this.getLoanDuration()) - 1)));
		return result;
	}

	/**
	 * The method returns the name of the bank in which the loan was taken
	 * 
	 * @return
	 */
	public abstract String getBankName();

	/**
	 * The method returns the type of loan
	 * 
	 * @return type of loan
	 */
	public abstract String getCreditType();

	/**
	 * The method checks the compliance of the entered loan repayment duration with
	 * the restrictions set by the bank
	 * 
	 * @param duration
	 * @return true if matched, false otherwise
	 */
	protected boolean checkLoanDurationInRange(int duration) {
		return this.limitParameters.getMinLoanDuration() <= duration
				&& duration <= this.limitParameters.getMaxLoanDuration();
	}

	/**
	 * The method checks the compliance of the entered loan amount with the
	 * restrictions set by the bank
	 * 
	 * @param loanSum
	 * @return true if matched, false otherwise
	 */
	protected boolean checkLoanAmountInRange(double loanSum) {
		return this.limitParameters.getMinLoanSum() <= loanSum && loanSum <= this.limitParameters.getMaxLoanSum();
	}

	/**
	 * The method implements a monthly payment on a loan. sumToPay cannot be less
	 * than monthlyPayment calculated upon initialization of the loan
	 * 
	 * @param sumToPay
	 * @throws AmountLessThanMonthlyPaymentException
	 * @throws LoanRepaymentExpirationException
	 */
	public void toPayMonthlyAmount(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException {
		if (this.isOpen) {
			if (sumToPay >= this.monthlyPayment) {
				this.checkLoanRepaymentPeriod();
				PaymentInformation information = this.paySum(sumToPay);
				this.paymentHistory[this.paymentsNumber - 1] = information;
			} else {
				throw new AmountLessThanMonthlyPaymentException(
						Resourcer.getString("messages.exceptions.loanRepaymentExceptionSum"));
			}
		}
	}

	/**
	 * The method calculates the percentage of the loan repayment
	 * 
	 * @return
	 */
	protected double calculatePercentPartMonthlyPayment() {
		return this.loanSum * this.loanRate / Credit.YEAR_TO_MONTHS_COEFFICIENT;
	}

	/**
	 * The method calculates the amount of debt remaining after payment
	 * 
	 * @param paymentSum
	 * @return
	 */
	protected double calculateLoanRemainingSum(double paymentSum) {
		return this.loanSum - (paymentSum - this.loanSum * this.loanRate / Credit.YEAR_TO_MONTHS_COEFFICIENT);
	}

	/**
	 * The method checks whether the payment number is included in the loan
	 * repayment period, if not, an exception is thrown, indicating that the loan is
	 * overdue
	 * 
	 * @throws LoanRepaymentExpirationException
	 */
	protected void checkLoanRepaymentPeriod() throws LoanRepaymentExpirationException {
		if (this.paymentsNumber >= this.loanDuration) {
			throw new LoanRepaymentExpirationException(
					Resourcer.getString("messages.exceptions.amountLessMonthlyPayment"));
		}
	}

	/**
	 * The method implements the payment of part of the debt
	 * 
	 * @param paymentSum
	 * @return Payment information
	 */
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

	/**
	 * Returns the entire amount paid to the bank (including loan interest and
	 * insurance payments)
	 * 
	 * @return
	 */
	public final double getAllPaidAmount() {
		float result = 0;
		for (int i = 0; i < this.paymentsNumber; i++) {
			result += paymentHistory[i].getPaymentAmount();
		}
		return result;
	}
}