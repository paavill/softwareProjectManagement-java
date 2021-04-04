package ru.rsreu.Chistyakov0518;

import java.util.Arrays;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.Credit;
import ru.rsreu.Chistyakov0518.credits.PaymentInformation;

public class CreditService {
	/**
	 * 
	 */
	private static final int TO_PERCENT_CONVERT_COEFFICIENT = 100;
	/**
	 * 
	 */
	private static final String FORMAT = "%4d %15.2f %3.1f %9.2f %9.2f %12.2f";
	/**
	 * 
	 */
	private static final String HANDLE_FORMAT = "%4s %15s %3s %9s %9s %12s";

	private CreditService() {

	}

	/**
	 * 
	 * @param credit
	 * @return
	 */
	public static String getFormatCreditPaymentHistory(Credit credit) {
		PaymentInformation[] paymentHistory = credit.getPaymentHistory();
		StringBuilder result = new StringBuilder("");
		if (credit.getPaymentsNumber() != 0) {
			result.append(String.format(CreditService.HANDLE_FORMAT, "¹",
					Resourcer.getString("messages.output.titles.remainingLoan"), "%",
					Resourcer.getString("messages.output.titles.payment"),
					Resourcer.getString("messages.output.titles.bodyPart"),
					Resourcer.getString("messages.output.titles.percentPart"))).append("\n");
			for (int i = 0; i < credit.getPaymentsNumber(); i++) {
				result.append(String.format(CreditService.FORMAT, paymentHistory[i].getPaymentNumber(),
						paymentHistory[i].getLoanSum(),
						(float) (paymentHistory[i].getLoanRate() * CreditService.TO_PERCENT_CONVERT_COEFFICIENT),
						paymentHistory[i].getPaymentAmount(), paymentHistory[i].getLoanPartPaymentAmount(),
						paymentHistory[i].getPercentPartPaymentAmount())).append("\n");
			}
			result.append(Resourcer.getString("messages.output.titles.paymentSum")).append(credit.getAllPaidAmount())
					.append("\n");
		} else {
			result.append(Resourcer.getString("messages.output.titles.noPayments")).append("\n");
		}
		return result.toString();
	}

	/**
	 * 
	 * @param credit
	 * @return
	 */
	public static String getFormatCreditInformation(Credit credit) {
		StringBuilder result = new StringBuilder();
		result.append(Resourcer.getString("messages.output.titles.bankName")).append(credit.getBankName()).append("\n")
				.append(Resourcer.getString("messages.output.titles.creditType")).append(credit.getCreditType())
				.append("\n").append(Resourcer.getString("messages.output.titles.loanDuration"))
				.append(credit.getLoanDuration()).append("\n")
				.append(Resourcer.getString("messages.output.titles.loanRate"))
				.append(credit.getLoanRate() * CreditService.TO_PERCENT_CONVERT_COEFFICIENT).append(" %").append("\n")
				.append(Resourcer.getString("messages.output.titles.loanSum")).append(credit.getLoanSum()).append("\n")
				.append(Resourcer.getString("messages.output.titles.state")).append(credit.getState()).append("\n");
		return result.toString();
	}

	/**
	 * 
	 * @param credits
	 */
	public static void sortCreditsByRate(Credit[] credits) {
		Arrays.sort(credits);
	}

	/**
	 * 
	 * @param credits
	 * @param searchingCredit
	 * @return
	 */
	public static Credit searchCredit(Credit[] credits, Credit searchingCredit) {
		int result = Arrays.binarySearch(credits, searchingCredit);
		if (result >= 0) {
			return credits[result];
		} else {
			return Credit.NULL_CREDIT;
		}
	}

	/**
	 * 
	 * @param credit
	 * @return
	 */
	public static String getAllCreditInformation(Credit credit) {
		StringBuilder result = new StringBuilder();
		result.append(CreditService.getFormatCreditInformation(credit))
				.append(CreditService.getFormatCreditPaymentHistory(credit));
		return result.toString();
	}
}
