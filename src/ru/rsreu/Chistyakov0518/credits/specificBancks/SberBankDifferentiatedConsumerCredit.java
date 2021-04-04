package ru.rsreu.Chistyakov0518.credits.specificBancks;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public class SberBankDifferentiatedConsumerCredit extends ConsumerCredit {
	private static final String BANK_NAME = Resourcer.getString("banks.name.sberBank");;
	private static final String CREDIT_TYPE = Resourcer.getString("banks.creditTypes.differentiated");
	/**
	 * 
	 */
	private static final int DEFAULT_MAX_LOAN_DURATION = 120;
	private static final int DEFAULT_MIN_LOAN_DURATION = 12;
	/**
	 * 
	 */
	private static final double DEFAULT_MAX_LOAN_SUM = 1e7;
	private static final double DEFAULT_MIN_LOAN_SUM = 3e4;
	/**
	 * 
	 */
	private static final double DEFAULT_LOW_BORD_HIGH_LOAN_SUM_RANGE = 5e6;
	private static final double DEFAULT_LOW_BORD_MIDDLE_LOAN_SUM_RANGE = 1.6e5;
	/**
	 * 
	 */
	private static final double DEFAULT_HIGH_RANGE_RATE = 0.065;
	private static final double DEFAULT_MIDDLE_RANGE_RATE = 0.075;
	private static final double DEFAULT_LOW_RANGE_RATE = 0.1;
	/**
	 * 
	 */
	private final double loanBody;

	public SberBankDifferentiatedConsumerCredit(double loanSum, int loanDuration) {
		super(loanSum, loanDuration,
				new LimitingLoanParametersOfferedBank(SberBankDifferentiatedConsumerCredit.DEFAULT_MAX_LOAN_SUM,
						SberBankDifferentiatedConsumerCredit.DEFAULT_MIN_LOAN_SUM,
						SberBankDifferentiatedConsumerCredit.DEFAULT_MIN_LOAN_DURATION,
						SberBankDifferentiatedConsumerCredit.DEFAULT_MAX_LOAN_DURATION),
				new DividingConsumerLoanAmountParametersOfferedBank(
						SberBankDifferentiatedConsumerCredit.DEFAULT_LOW_BORD_HIGH_LOAN_SUM_RANGE,
						SberBankDifferentiatedConsumerCredit.DEFAULT_LOW_BORD_MIDDLE_LOAN_SUM_RANGE,
						SberBankDifferentiatedConsumerCredit.DEFAULT_HIGH_RANGE_RATE,
						SberBankDifferentiatedConsumerCredit.DEFAULT_MIDDLE_RANGE_RATE,
						SberBankDifferentiatedConsumerCredit.DEFAULT_LOW_RANGE_RATE));
		this.loanBody = this.getLoanSum() / this.getLoanDuration();
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
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

	@Override
	public String getBankName() {
		return SberBankDifferentiatedConsumerCredit.BANK_NAME;
	}

	@Override
	public String getCreditType() {
		return SberBankDifferentiatedConsumerCredit.CREDIT_TYPE;
	}

}
