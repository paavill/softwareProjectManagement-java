package ru.rsreu.Chistyakov0518.credits.specificBancks;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.*;

public class AlphaBankAnnuityConsumerCredit extends ConsumerCredit {
	private static final String BANK_NAME = Resourcer.getString("banks.name.alphaBank");
	private static final String CREDIT_TYPE = Resourcer.getString("banks.creditTypes.Annuity");
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
	private static final double DEFAULT_LOW_BORD_HIGH_LOAN_SUM_RANGE = 1.3e6;
	private static final double DEFAULT_LOW_BORD_MIDDLE_LOAN_SUM_RANGE = 1.6e5;
	/**
	 * 
	 */
	private static final double DEFAULT_HIGH_RANGE_RATE = 0.055;
	private static final double DEFAULT_MIDDLE_RANGE_RATE = 0.065;
	private static final double DEFAULT_LOW_RANGE_RATE = 0.07;

	/**
	 * 
	 * @param loanSum
	 * @param loanDuration
	 * @throws IncorrectLoanAmountException
	 * @throws IncorrectLoanDurationException
	 */
	public AlphaBankAnnuityConsumerCredit(double loanSum, int loanDuration) {
		super(loanSum, loanDuration,
				new LimitingLoanParametersOfferedBank(AlphaBankAnnuityConsumerCredit.DEFAULT_MAX_LOAN_SUM,
						AlphaBankAnnuityConsumerCredit.DEFAULT_MIN_LOAN_SUM,
						AlphaBankAnnuityConsumerCredit.DEFAULT_MIN_LOAN_DURATION,
						AlphaBankAnnuityConsumerCredit.DEFAULT_MAX_LOAN_DURATION),
				new DividingConsumerLoanAmountParametersOfferedBank(
						AlphaBankAnnuityConsumerCredit.DEFAULT_LOW_BORD_HIGH_LOAN_SUM_RANGE,
						AlphaBankAnnuityConsumerCredit.DEFAULT_LOW_BORD_MIDDLE_LOAN_SUM_RANGE,
						AlphaBankAnnuityConsumerCredit.DEFAULT_HIGH_RANGE_RATE,
						AlphaBankAnnuityConsumerCredit.DEFAULT_MIDDLE_RANGE_RATE,
						AlphaBankAnnuityConsumerCredit.DEFAULT_LOW_RANGE_RATE));
		this.setMonthlyPayment(this.calculateMonthPaymentAmount());
	}

	@Override
	public String getBankName() {
		return AlphaBankAnnuityConsumerCredit.BANK_NAME;
	}

	@Override
	public String getCreditType() {
		return AlphaBankAnnuityConsumerCredit.CREDIT_TYPE;
	}

}
