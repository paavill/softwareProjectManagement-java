package ru.rsreu.Chistyakov0518;

import java.util.Locale;

import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	public static void main(String[] args) throws LoanRepaymentExpirationException {
		Locale.setDefault(Locale.US);

		Credit[] bankOfferts = CreditInitializer.initializeCredits();

		for (Credit credit : bankOfferts) {
			try {
				System.out.println(CreditService.getAllCreditInformation(credit));
				for (int i = 0; i < credit.getLoanDuration(); i++) {
					credit.toPayMonthlyAmount(credit.getMonthlyPayment());
				}
				System.out.println(CreditService.getAllCreditInformation(credit));
			} catch (AmountLessThanMonthlyPaymentException e) {
				e.printStackTrace();
			} catch (LoanRepaymentExpirationException e) {
				e.printStackTrace();
			}
		}
	}

}
