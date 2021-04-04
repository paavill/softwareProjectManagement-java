package ru.rsreu.Chistyakov0518;

import java.util.Locale;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

/**
 * 
 * @author Chistyakov Pavel
 *
 */
public class ApplicationRunner {

	private ApplicationRunner() {

	}

	/**
	 * Entry point
	 * 
	 * @param args - Launch parameters
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		StringBuilder processResult = new StringBuilder();
		Credit[] bankOfferts = CreditInitializer.initializeCredits();

		for (Credit credit : bankOfferts) {
			try {
				processResult.append(Resourcer.getString("messages.output.beforePayments")).append("\n");
				processResult.append(CreditService.getAllCreditInformation(credit)).append("\n");
				/** Loan repayment */
				for (int i = 0; i < credit.getLoanDuration(); i++) {
					credit.toPayMonthlyAmount(credit.getMonthlyPayment());
				}
				processResult.append(Resourcer.getString("messages.output.afterPayments")).append("\n");
				processResult.append(CreditService.getAllCreditInformation(credit)).append("\n");
			} catch (AmountLessThanMonthlyPaymentException e) {
				e.printStackTrace();
			} catch (LoanRepaymentExpirationException e) {
				e.printStackTrace();
			}
		}
		Credit searchResult = CreditService.searchCredit(bankOfferts, CreditInitializer.getCreditToSearch());
		processResult.append(Resourcer.getString("messages.output.requiredLoan")).append("\n");
		processResult.append(CreditService.getFormatCreditInformation(searchResult)).append("\n");
		CreditService.sortCreditsByRate(bankOfferts);
		processResult.append(Resourcer.getString("messages.output.sortByRateCredit")).append("\n");
		for (Credit credit : bankOfferts) {
			processResult.append(CreditService.getFormatCreditInformation(credit)).append("\n");
		}
		System.out.print(processResult);
	}

}
