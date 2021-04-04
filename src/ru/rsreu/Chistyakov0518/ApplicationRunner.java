package ru.rsreu.Chistyakov0518;

import java.util.Locale;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Credit[] bankOfferts = CreditInitializer.initializeCredits();

		for (Credit credit : bankOfferts) {
			try {
				System.out.println(Resourcer.getString("messages.output.beforePayments"));
				System.out.println(CreditService.getAllCreditInformation(credit));
				for (int i = 0; i < credit.getLoanDuration(); i++) {
					credit.toPayMonthlyAmount(credit.getMonthlyPayment());
				}
				System.out.println(Resourcer.getString("messages.output.afterPayments"));
				System.out.println(CreditService.getAllCreditInformation(credit));
			} catch (AmountLessThanMonthlyPaymentException e) {
				e.printStackTrace();
			} catch (LoanRepaymentExpirationException e) {
				e.printStackTrace();
			}
		}
		Credit searchResult = CreditService.searchCredit(bankOfferts, bankOfferts[0]);
		System.out.println(CreditService.getAllCreditInformation(searchResult));
		CreditService.sortCreditsByRate(bankOfferts);
		for (Credit credit : bankOfferts) {
			System.out.println(CreditService.getFormatCreditInformation(credit));
		}
	}

}
