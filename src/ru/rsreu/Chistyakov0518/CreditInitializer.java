package ru.rsreu.Chistyakov0518;

import ru.rsreu.Chistyakov0518.credits.Credit;
import ru.rsreu.Chistyakov0518.credits.specificBancks.*;

public class CreditInitializer {
	private static final double DEFAULT_SUM = 1.5e6;
	private static final int DEFAULT_DURATION = 24;
	private static final double INSURANCE_FEE = 1.3e3;

	private static final Credit TINKOFF_CREDIT = new TinkoffBankMortgageCredit(CreditInitializer.DEFAULT_SUM,
			CreditInitializer.DEFAULT_DURATION, CreditInitializer.INSURANCE_FEE);

	private static final Credit SBER_CREDIT = new SberBankDifferentiatedConsumerCredit(CreditInitializer.DEFAULT_SUM,
			CreditInitializer.DEFAULT_DURATION);

	private static final Credit ALPHA_CREDIT = new AlphaBankAnnuityConsumerCredit(CreditInitializer.DEFAULT_SUM,
			CreditInitializer.DEFAULT_DURATION);

	private CreditInitializer() {

	}

	public static Credit[] initializeCredits() {
		return new Credit[] { CreditInitializer.TINKOFF_CREDIT, CreditInitializer.SBER_CREDIT,
				CreditInitializer.ALPHA_CREDIT };
	}

}
