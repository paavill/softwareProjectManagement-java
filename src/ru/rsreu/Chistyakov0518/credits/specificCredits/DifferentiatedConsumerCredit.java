package ru.rsreu.Chistyakov0518.credits.specificCredits;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.Bank;
import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

/**
 * Class differentiated loan provided by Sber Bank
 * 
 * @author Chistyakov Pavel
 *
 */
public class DifferentiatedConsumerCredit extends AbstractCredit {

	private static final String CREDIT_TYPE = Resourcer.getString("banks.creditTypes.differentiated");

	private final double loanBody;

	public DifferentiatedConsumerCredit(double loanSum, int loanDuration, Bank bank) {
		super(loanSum, loanDuration, bank, DifferentiatedConsumerCredit.CREDIT_TYPE);
		this.loanBody = this.getLoanSum() / this.getLoanDuration();
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
	}

	protected double calculateLoanRemainingSum(double paymentSum) {
		return this.getLoanSum() - this.loanBody;
	}

	@Override
	public double calculateMonthPaymentAmount() {
		double percentPart = this.calculatePercentPartMonthlyPayment();
		return this.loanBody + percentPart;
	}

	@Override
	public void toPayMonthlyAmount(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException {
		super.toPayMonthlyAmount(sumToPay);
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
	}

}
