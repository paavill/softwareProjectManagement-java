package ru.rsreu.Chistyakov0518.credits;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.Bank;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

/**
 * 
 * @author Chistyakov Pavel
 *
 */
public abstract class AbstractCredit implements Comparable<AbstractCredit> {

	/**
	 * NullObject pattern implementation
	 */
	public static final AbstractCredit NULL_CREDIT = new AbstractCredit() {

		@Override
		public int compareTo(AbstractCredit o) {
			return 0;
		}

		@Override
		public double calculateMonthPaymentAmount() {
			return 0;
		}

		@Override
		protected double calculateLoanRemainingSum(double paymentSum) {
			// TODO Auto-generated method stub
			return 0;
		}

	};

	private static final int YEAR_TO_MONTHS_COEFFICIENT = 12;

	private final String creditType;

	/**
	 * The field stores the status of the loan, if the loan is open - true, if
	 * closed - false
	 */
	private boolean isOpen = true;

	/**
	 * The field that stores the object of the bank that issued the loan
	 */
	private Bank loanProvider;

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

	private AbstractCredit() {
		this.creditType = "null";
	}

	/**
	 * 
	 * @param loanSum
	 * @param loanDuration
	 */
	public AbstractCredit(double loanSum, int loanDuration, Bank loanProvider, String type) {
		this.loanProvider = loanProvider;
		this.creditType = type;
		this.loanRate = loanProvider.getLoanRate();
		this.loanSum = loanSum;
		this.loanDuration = loanDuration;
		this.paymentHistory = new PaymentInformation[loanDuration];
		this.paymentsNumber = 0;
	}

	protected static final int getYearToMonthsCoefficient() {
		return AbstractCredit.YEAR_TO_MONTHS_COEFFICIENT;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	protected void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public final boolean getState() {
		return this.isOpen;
	}

	public final int getPaymentsNumber() {
		return paymentsNumber;
	}

	public final Bank getLoanProvider() {
		return loanProvider;
	}

	public final PaymentInformation[] getPaymentHistory() {
		return this.paymentHistory;
	}

	public final double getLoanSum() {
		return loanSum;
	}

	public final int getLoanDuration() {
		return loanDuration;
	}

	public String getCreditType() {
		return this.creditType;
	}

	/**
	 * Method calculating monthly loan payment
	 * 
	 * @return monthly loan payment
	 */
	public abstract double calculateMonthPaymentAmount();

	/**
	 * The method calculates the amount of debt remaining after payment
	 * 
	 * @param paymentSum
	 * @return
	 */
	protected abstract double calculateLoanRemainingSum(double paymentSum);

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
	protected final double calculatePercentPartMonthlyPayment() {
		return this.loanSum * this.loanRate / AbstractCredit.YEAR_TO_MONTHS_COEFFICIENT;
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
			this.isOpen = false;
			double paymentRemainderDifference = paymentSum - this.loanSum;
			paymentBody -= paymentRemainderDifference;
			this.loanSum = 0;
			information = new PaymentInformation(this.loanSum, this.paymentsNumber, this.loanRate,
					paymentBody + percentPart, percentPart, paymentBody);
		}
		return information;
	}

	@Override
	public int compareTo(AbstractCredit o) {
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