package ru.rsreu.Chistyakov0518.credits;

import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public class AlphaBankAnnuityConsumerCredit extends ConsumerCredit {
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
	 * @param loanSum
	 * @param loanDuration
	 * @throws IncorrectLoanAmountException
	 * @throws IncorrectLoanDurationException
	 */
	public AlphaBankAnnuityConsumerCredit(double loanSum, int loanDuration)
			throws IncorrectLoanAmountException, IncorrectLoanDurationException {
		super(loanSum, loanDuration, AlphaBankAnnuityConsumerCredit.DEFAULT_MAX_LOAN_SUM,
				AlphaBankAnnuityConsumerCredit.DEFAULT_MIN_LOAN_SUM,
				AlphaBankAnnuityConsumerCredit.DEFAULT_MAX_LOAN_DURATION,
				AlphaBankAnnuityConsumerCredit.DEFAULT_MIN_LOAN_DURATION);
		this.setLoanRate(this.getLoanRateMatchSum(loanSum));
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
		this.setPaymentsNumber(0);
	}

	private double getLoanRateMatchSum(double loanSum) {
		if (this.isLoanSumInHighLoanRange(loanSum)) {
			return AlphaBankAnnuityConsumerCredit.HIGH_RANGE_RATE;
		} else if (AlphaBankAnnuityConsumerCredit.isLoanSumInMiddleLoanRange(loanSum)) {
			return AlphaBankAnnuityConsumerCredit.MIDLE_RANGE_RATE;
		} else {
			return AlphaBankAnnuityConsumerCredit.LOW_RANGE_RATE;
		}
	}

	private boolean isLoanSumInHighLoanRange(double loanSum) {
		return AlphaBankAnnuityConsumerCredit.LOW_BORD_HIGH_LOAN_SUM_RANGE < loanSum
				&& loanSum <= this.getMaxLoanSum();
	}

	private static boolean isLoanSumInMiddleLoanRange(double loanSum) {
		return AlphaBankAnnuityConsumerCredit.LOW_BORD_MIDDLE_LOAN_SUM_RANGE < loanSum
				&& loanSum <= AlphaBankAnnuityConsumerCredit.LOW_BORD_HIGH_LOAN_SUM_RANGE;
	}

	@Override
	public double calculateMonthPaymentAmount() {
		double monthLoanRate = this.getLoanRate() / Credit.getYearToMonthsCoefficient();
		double result = (this.getLoanSum()
				* (monthLoanRate + (double) monthLoanRate / (Math.pow(1 + monthLoanRate, this.getLoanDuration()) - 1)));
		return result;
	}

	@Override
	public void toPayMonthlyAmount() throws LoanRepaymentExpirationException {
		this.checkLoanRepaymentPeriod();
		PaymentInformation information = this.paySum(this.getMonthlyPayment());
		this.setPaymentInformation(this.getPaymentsNumber() - 1, information);
	}

	@Override
	public void toPayAmountMoreMonthSum(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException {
		if (sumToPay >= this.getMonthlyPayment()) {
			this.checkLoanRepaymentPeriod();
			PaymentInformation information = this.paySum(sumToPay);
			this.setPaymentInformation(this.getPaymentsNumber() - 1, information);
		} else {
			throw new AmountLessThanMonthlyPaymentException("less");
		}
	}

}
