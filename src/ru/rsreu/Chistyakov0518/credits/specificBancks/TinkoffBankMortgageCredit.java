package ru.rsreu.Chistyakov0518.credits.specificBancks;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

/**
 * Class a mortgage loan provided by Tinkoff Bank
 * 
 * @author Chistyakov Pavel
 *
 */
public class TinkoffBankMortgageCredit extends MortgageCredit {
	private static final String BANK_NAME = Resourcer.getString("banks.name.tinkoff");;
	private static final String CREDIT_TYPE = Resourcer.getString("banks.creditTypes.mortgage");

	private static final int DEFAULT_MAX_LOAN_DURATION = 360;
	private static final int DEFAULT_MIN_LOAN_DURATION = 12;

	private static final double DEFAULT_MAX_LOAN_SUM = 1e7;
	private static final double DEFAULT_MIN_LOAN_SUM = 3e4;

	private static final double DEFAULT_RATE = 0.073;

	public TinkoffBankMortgageCredit(double loanSum, int loanDuration, double insuranceFee) {
		super(loanSum, loanDuration, TinkoffBankMortgageCredit.DEFAULT_RATE,
				new LimitingLoanParametersOfferedBank(TinkoffBankMortgageCredit.DEFAULT_MAX_LOAN_SUM,
						TinkoffBankMortgageCredit.DEFAULT_MIN_LOAN_SUM,
						TinkoffBankMortgageCredit.DEFAULT_MIN_LOAN_DURATION,
						TinkoffBankMortgageCredit.DEFAULT_MAX_LOAN_DURATION),
				insuranceFee);
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
	}

	@Override
	public void toPayMonthlyAmount(double sumToPay)
			throws AmountLessThanMonthlyPaymentException, LoanRepaymentExpirationException {
		super.toPayMonthlyAmount(sumToPay + this.getInsuranceFee());
	}

	@Override
	public String getBankName() {
		return TinkoffBankMortgageCredit.BANK_NAME;
	}

	@Override
	public String getCreditType() {
		return TinkoffBankMortgageCredit.CREDIT_TYPE;
	}
}
