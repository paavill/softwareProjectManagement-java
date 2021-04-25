package ru.rsreu.Chistyakov0518.credits.specificCredits;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.Bank;
import ru.rsreu.Chistyakov0518.credits.*;

/**
 * Class annuity loan
 * 
 * @author Chistyakov Pavel
 *
 */
public class AnnuityConsumerCredit extends AbstractCredit {

	private static final String CREDIT_TYPE = Resourcer.getString("banks.creditTypes.Annuity");

	/**
	 * 
	 * @param loanSum
	 * @param loanDuration
	 */
	public AnnuityConsumerCredit(double loanSum, int loanDuration, Bank bank) {
		super(loanSum, loanDuration, bank, AnnuityConsumerCredit.CREDIT_TYPE);
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
	}

	@Override
	protected double calculateLoanRemainingSum(double paymentSum) {
		return this.getLoanSum() - (paymentSum - this.getLoanSum() * this.getLoanProvider().getLoanRate()
				/ AbstractCredit.getYearToMonthsCoefficient());
	}

	@Override
	public double calculateMonthPaymentAmount() {
		double monthLoanRate = this.getLoanProvider().getLoanRate() / AbstractCredit.getYearToMonthsCoefficient();
		double result = (this.getLoanSum()
				* (monthLoanRate + (double) monthLoanRate / (Math.pow(1 + monthLoanRate, this.getLoanDuration()) - 1)));
		return result;
	}

}
