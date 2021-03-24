package ru.rsreu.Chistyakov0318;

import java.util.Locale;
import com.prutzkow.resourcer.Resourcer;

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
		int result;

		Locale.setDefault(Locale.US);

		BetweenNegativeMultiplier numbersSet = new BetweenNegativeMultiplier(-1, 1, 3, -1, -1, 1, 1, 5, -1);
		try {
			result = numbersSet.betweenNegativeMultiply();
			System.out.println(Resourcer.getString("string.sourceArr") + numbersSet.toString()
					+ Resourcer.getString("string.result") + result);
		} catch (InvalidNegativeIndexNumbException exc) {
			System.out.println(exc.getMessage());
		}

	}

}
