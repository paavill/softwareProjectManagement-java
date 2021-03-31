package ru.rsreu.Chistyakov0518.credits;

import ru.rsreu.Chistyakov0518.credits.exceptions.AmountLessThanMonthlyPaymentException;
import ru.rsreu.Chistyakov0518.credits.exceptions.IncorrectLoanAmountException;
import ru.rsreu.Chistyakov0518.credits.exceptions.IncorrectLoanDurationException;
import ru.rsreu.Chistyakov0518.credits.exceptions.LoanRepaymentExpirationException;

public class SberBankDifferentiatedConsumerCredit extends ConsumerCredit {
	/**
	 * 
	 */
	private static final int DEFAULT_MAX_LOAN_DURATION = 60;
	private static final int DEFAULT_MIN_LOAN_DURATION = 12;
	/**
	 * 
	 */
	private static final double DEFAULT_MAX_LOAN_SUM = 5e6;
	private static final double DEFAULT_MIN_LOAN_SUM = 5e4;
	/**
	 * 
	 */
	private static final double LOW_BORD_HIGH_LOAN_SUM_RANGE = 1.3e6;
	private static final double LOW_BORD_MIDDLE_LOAN_SUM_RANGE = 1.6e5;
	/**
	 * 
	 */
	private static final double HIGH_RANGE_RATE = 0.055;
	private static final double MIDLE_RANGE_RATE = 0.065;
	private static final double LOW_RANGE_RATE = 0.07;
	/**
	 * 
	 */
	private final double loanBody;

	public SberBankDifferentiatedConsumerCredit(double loanSum, int loanDuration)
			throws IncorrectLoanDurationException, IncorrectLoanAmountException {
		super(loanSum, loanDuration, SberBankDifferentiatedConsumerCredit.DEFAULT_MAX_LOAN_SUM,
				SberBankDifferentiatedConsumerCredit.DEFAULT_MIN_LOAN_SUM,
				SberBankDifferentiatedConsumerCredit.DEFAULT_MAX_LOAN_DURATION,
				SberBankDifferentiatedConsumerCredit.DEFAULT_MIN_LOAN_DURATION);
		this.setLoanRate(this.getLoanRateMatchSum(loanSum));
		this.loanBody = this.getLoanSum() / this.getLoanDuration();
	}
	
	private double getLoanRateMatchSum(double loanSum) {
		if (this.isLoanSumInHighLoanRange(loanSum)) {
			return SberBankDifferentiatedConsumerCredit.HIGH_RANGE_RATE;
		} else if (SberBankDifferentiatedConsumerCredit.isLoanSumInMiddleLoanRange(loanSum)) {
			return SberBankDifferentiatedConsumerCredit.MIDLE_RANGE_RATE;
		} else {
			return SberBankDifferentiatedConsumerCredit.LOW_RANGE_RATE;
		}
	}
	
	private boolean isLoanSumInHighLoanRange(double loanSum) {
		return SberBankDifferentiatedConsumerCredit.LOW_BORD_HIGH_LOAN_SUM_RANGE < loanSum
				&& loanSum <= this.getMaxLoanSum();
	}

	private static boolean isLoanSumInMiddleLoanRange(double loanSum) {
		return SberBankDifferentiatedConsumerCredit.LOW_BORD_MIDDLE_LOAN_SUM_RANGE < loanSum
				&& loanSum <= SberBankDifferentiatedConsumerCredit.LOW_BORD_HIGH_LOAN_SUM_RANGE;
	}
	
	@Override
	public double calculateMonthPaymentAmount() {
		double percentPart = this.calculatePercentPartMonthlyPayment();
		return this.loanBody + percentPart;
	}

	@Override
	public void toPayMonthlyAmount() throws LoanRepaymentExpirationException {
		this.checkLoanRepaymentPeriod();
		double paymentSum = this.calculateMonthPaymentAmount();
		PaymentInformation information = this.paySum(paymentSum);
		this.setPaymentInformation(this.getPaymentsNumber() - 1, information);
	}

	@Override
	public void toPayAmountMoreMonthSum(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException {
		// TODO Auto-generated method stub

	}

}
