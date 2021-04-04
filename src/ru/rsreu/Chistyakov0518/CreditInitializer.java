package ru.rsreu.Chistyakov0518;

import ru.rsreu.Chistyakov0518.credits.Credit;
import ru.rsreu.Chistyakov0518.credits.specificBancks.*;

/**
 * 
 * A utility class that initializes the main types of credits and returns them
 * as an array
 * 
 * @author pavel
 *
 */
public class CreditInitializer {
	/**
	 * Default Values ​​for Initializing Loans
	 */
	private static final double DEFAULT_SUM = 1.5e6;
	private static final int DEFAULT_DURATION = 24;
	/**
	 * Standard insurance payment
	 */
	private static final double INSURANCE_FEE = 1.3e3;

	private static final Credit TINKOFF_CREDIT = new TinkoffBankMortgageCredit(CreditInitializer.DEFAULT_SUM,
			CreditInitializer.DEFAULT_DURATION, CreditInitializer.INSURANCE_FEE);

	private static final Credit SBER_CREDIT = new SberBankDifferentiatedConsumerCredit(CreditInitializer.DEFAULT_SUM,
			CreditInitializer.DEFAULT_DURATION);

	private static final Credit ALPHA_CREDIT = new AlphaBankAnnuityConsumerCredit(CreditInitializer.DEFAULT_SUM,
			CreditInitializer.DEFAULT_DURATION);

	private CreditInitializer() {

	}

	/**
	 * Returns an array of initialized credits
	 * 
	 * @return
	 */
	public static Credit[] initializeCredits() {
		return new Credit[] { CreditInitializer.TINKOFF_CREDIT, CreditInitializer.SBER_CREDIT,
				CreditInitializer.ALPHA_CREDIT };
	}

	public static Credit getCreditToSearch() {
		return CreditInitializer.ALPHA_CREDIT;
	}

}
