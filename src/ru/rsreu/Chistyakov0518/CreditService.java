package ru.rsreu.Chistyakov0518;

import java.util.Arrays;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0518.credits.AbstractCredit;
import ru.rsreu.Chistyakov0518.credits.PaymentInformation;

/**
 * 
 * @author Chistyakov Pavel
 *
 */
public class CreditService {

	private static final int TO_PERCENT_CONVERT_COEFFICIENT = 100;
	/**
	 * Constant for formatting payout history data
	 */
	private static final String DATA_FORMAT = "%4d %15.2f %3.1f %9.2f %9.2f %12.2f";
	/**
	 * Constant for formatting the title of the payout history table
	 */
	private static final String HANDLE_FORMAT = "%4s %15s %3s %9s %9s %12s";

	private CreditService() {

	}

	/**
	 * The method converts the history of loan payments into a formatted string
	 * 
	 * @param credit
	 * @return formatted string with information
	 */
	public static String getFormatCreditPaymentHistory(AbstractCredit credit) {
		PaymentInformation[] paymentHistory = credit.getPaymentHistory();
		StringBuilder result = new StringBuilder("");
		if (credit.getPaymentsNumber() != 0) {
			result.append(String.format(CreditService.HANDLE_FORMAT, "¹",
					Resourcer.getString("messages.output.titles.remainingLoan"), "%",
					Resourcer.getString("messages.output.titles.payment"),
					Resourcer.getString("messages.output.titles.bodyPart"),
					Resourcer.getString("messages.output.titles.percentPart"))).append("\n");
			for (int i = 0; i < credit.getPaymentsNumber(); i++) {
				result.append(String.format(CreditService.DATA_FORMAT, paymentHistory[i].getPaymentNumber(),
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
	 * The method converts loan information into a formatted string
	 * 
	 * @param credit
	 * @return formatted string with information
	 */
	public static String getFormatCreditInformation(AbstractCredit credit) {
		StringBuilder result = new StringBuilder();
		result.append(Resourcer.getString("messages.output.titles.bankName"))
				.append(credit.getLoanProvider().getBankName()).append("\n")
				.append(Resourcer.getString("messages.output.titles.creditType")).append(credit.getCreditType())
				.append("\n").append(Resourcer.getString("messages.output.titles.loanDuration"))
				.append(credit.getLoanDuration()).append("\n")
				.append(Resourcer.getString("messages.output.titles.loanRate"))
				.append(credit.getLoanProvider().getLoanRate() * CreditService.TO_PERCENT_CONVERT_COEFFICIENT)
				.append(" %").append("\n").append(Resourcer.getString("messages.output.titles.loanSum"))
				.append(credit.getLoanSum()).append("\n").append(Resourcer.getString("messages.output.titles.state"))
				.append(credit.getState()).append("\n");
		return result.toString();
	}

	/**
	 * Sorts the array
	 * 
	 * @param credits
	 */
	public static void sortCreditsByRate(AbstractCredit[] credits) {
		Arrays.sort(credits);
	}

	/**
	 * Finds the required loan from an array of loans, in case the loan is not
	 * found, it returns NULL_CREDIT
	 * 
	 * @param credits
	 * @param searchingCredit
	 * @return required credit
	 */
	public static AbstractCredit searchCredit(AbstractCredit[] credits, AbstractCredit searchingCredit) {
		int result = Arrays.binarySearch(credits, searchingCredit);
		if (result >= 0) {
			return credits[result];
		} else {
			return AbstractCredit.NULL_CREDIT;
		}
	}

	/**
	 * 
	 * The method converts all information about the loan (payment history and basic
	 * parameters) into a formatted string
	 * 
	 * @param credit
	 * @return formatted string with information
	 */
	public static String getAllCreditInformation(AbstractCredit credit) {
		StringBuilder result = new StringBuilder();
		result.append(CreditService.getFormatCreditInformation(credit))
				.append(CreditService.getFormatCreditPaymentHistory(credit));
		return result.toString();
	}
}
