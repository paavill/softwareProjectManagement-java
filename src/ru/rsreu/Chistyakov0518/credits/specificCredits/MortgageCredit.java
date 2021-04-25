package ru.rsreu.Chistyakov0518.credits.specificCredits;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.Bank;
import ru.rsreu.Chistyakov0518.credits.*;

/**
 * Class a mortgage loan provided by Tinkoff Bank
 * 
 * @author Chistyakov Pavel
 *
 */
public class MortgageCredit extends AbstractCredit {
	private static final String CREDIT_TYPE = Resourcer.getString("banks.creditTypes.mortgage");

	/**
	 * The amount of mandatory insurance payments for a mortgage loan.
	 */
	private final double insuranceFee;

	public MortgageCredit(double loanSum, int loanDuration, Bank bank, double insuranceFee) {
		super(loanSum, loanDuration, bank, MortgageCredit.CREDIT_TYPE);
		this.insuranceFee = insuranceFee;
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
	}

	@Override
	protected double calculateLoanRemainingSum(double paymentSum) {
		return this.getLoanSum() - (paymentSum - this.insuranceFee - this.getLoanSum()
				* this.getLoanProvider().getLoanRate() / AbstractCredit.getYearToMonthsCoefficient());
	}

	@Override
	public double calculateMonthPaymentAmount() {
		double monthLoanRate = this.getLoanProvider().getLoanRate() / AbstractCredit.getYearToMonthsCoefficient();
		double result = (this.getLoanSum()
				* (monthLoanRate + (double) monthLoanRate / (Math.pow(1 + monthLoanRate, this.getLoanDuration()) - 1)));
		return result + this.insuranceFee;
	}
}
