package ru.rsreu.Chistyakov0518;

import ru.rsreu.Chistyakov0518.credits.*;
import ru.rsreu.Chistyakov0518.credits.exceptions.*;

public class ApplicationRunner {

	private static final int MONTH = 12;
	private static final int SUM = 200000;

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		String a = "";
		try {
			Credit n = new SberBankDifferentiatedConsumerCredit(SUM, MONTH);
			Credit b = new AlphaBankAnnuityConsumerCredit(SUM, MONTH);

			for (int i = 0; i < MONTH; i++) {
				n.toPayMonthlyAmount();
				b.toPayMonthlyAmount();
			}
			StringBuilder result = n.getPaymentHistory();
			StringBuilder resultb = b.getPaymentHistory();
			
			
			System.out.println(result.toString());
			System.out.println(resultb.toString());
			System.out.println(n.getAllPaidAmount());
			System.out.println(n.getLoanRate());
		} catch (IncorrectLoanAmountException | IncorrectLoanDurationException exception) {
			System.out.print(exception.getMessage());
		} catch (LoanRepaymentExpirationException exception) {
			System.out.println(exception.getMessage());
		}
	}

}
