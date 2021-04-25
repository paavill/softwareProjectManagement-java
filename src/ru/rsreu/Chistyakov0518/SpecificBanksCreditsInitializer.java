package ru.rsreu.Chistyakov0518;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.AbstractCredit;
import ru.rsreu.Chistyakov0518.credits.specificCredits.*;

/**
 * 
 * A utility class that initializes the main types of credits and returns them
 * as an array
 * 
 * @author pavel
 *
 */
public class SpecificBanksCreditsInitializer {

	/**
	 * Default Values ​​for Initializing Loans
	 */
	private static final int DEFAULT_MAX_LOAN_DURATION = 60;
	private static final int DEFAULT_MIN_LOAN_DURATION = 12;

	private static final double DEFAULT_MAX_LOAN_SUM = 5e6;
	private static final double DEFAULT_MIN_LOAN_SUM = 5e4;

	private static final double DEFAULT_SUM = 1.5e6;
	private static final int DEFAULT_DURATION = 24;

	private static final Bank TINKOFF = new Bank(Resourcer.getString("banks.name.tinkoff"), 0.073,
			SpecificBanksCreditsInitializer.DEFAULT_MIN_LOAN_DURATION,
			SpecificBanksCreditsInitializer.DEFAULT_MAX_LOAN_DURATION,
			SpecificBanksCreditsInitializer.DEFAULT_MIN_LOAN_SUM, SpecificBanksCreditsInitializer.DEFAULT_MAX_LOAN_SUM);

	private static final Bank SBER = new Bank(Resourcer.getString("banks.name.sberBank"), 0.12,
			SpecificBanksCreditsInitializer.DEFAULT_MIN_LOAN_DURATION,
			SpecificBanksCreditsInitializer.DEFAULT_MAX_LOAN_DURATION,
			SpecificBanksCreditsInitializer.DEFAULT_MIN_LOAN_SUM, SpecificBanksCreditsInitializer.DEFAULT_MAX_LOAN_SUM);

	private static final Bank ALPHA = new Bank(Resourcer.getString("banks.name.alphaBank"), 0.06,
			SpecificBanksCreditsInitializer.DEFAULT_MIN_LOAN_DURATION,
			SpecificBanksCreditsInitializer.DEFAULT_MAX_LOAN_DURATION,
			SpecificBanksCreditsInitializer.DEFAULT_MIN_LOAN_SUM, SpecificBanksCreditsInitializer.DEFAULT_MAX_LOAN_SUM);

	/**
	 * Standard amount of mandatory insurance payments for a mortgage loan.
	 */
	private static final double INSURANCE_FEE = 1.3e4;

	private static final AbstractCredit TINKOFF_MORTGAGE_CREDIT = new MortgageCredit(
			SpecificBanksCreditsInitializer.DEFAULT_SUM, SpecificBanksCreditsInitializer.DEFAULT_DURATION,
			SpecificBanksCreditsInitializer.TINKOFF, SpecificBanksCreditsInitializer.INSURANCE_FEE);

	private static final AbstractCredit ALPHA_DIFFERENTIATED_CREDIT = new DifferentiatedConsumerCredit(
			SpecificBanksCreditsInitializer.DEFAULT_SUM, SpecificBanksCreditsInitializer.DEFAULT_DURATION,
			SpecificBanksCreditsInitializer.ALPHA);

	private static final AbstractCredit SBER_ANNUITY_CREDIT = new AnnuityConsumerCredit(
			SpecificBanksCreditsInitializer.DEFAULT_SUM, SpecificBanksCreditsInitializer.DEFAULT_DURATION,
			SpecificBanksCreditsInitializer.SBER);

	private SpecificBanksCreditsInitializer() {

	}

	/**
	 * Returns an array of initialized credits
	 * 
	 * @return
	 */
	public static AbstractCredit[] initializeCredits() {
		return new AbstractCredit[] { SpecificBanksCreditsInitializer.TINKOFF_MORTGAGE_CREDIT,
				SpecificBanksCreditsInitializer.ALPHA_DIFFERENTIATED_CREDIT,
				SpecificBanksCreditsInitializer.SBER_ANNUITY_CREDIT };
	}

	public static AbstractCredit getCreditToSearch() {
		return SpecificBanksCreditsInitializer.SBER_ANNUITY_CREDIT;
	}

}
