package ru.rsreu.Chistyakov0308;

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
		Locale.setDefault(Locale.US);
		AfterZeroNumberSubtractor numbersSet = new AfterZeroNumberSubtractor(0, 0, 0, 0, 0, -1, 25, -234, -2, 4, 4);
		try {
			StringBuilder result = new StringBuilder(Resourcer.getString("string.sourceArr"));
			result.append(numbersSet.toString()).append("\n");
			numbersSet.subtractSubtrahendFromNegativeElementsAfterZeroSequentialNumber();
			result.append(Resourcer.getString("string.result")).append(numbersSet.toString()).append("\n");
			System.out.print(result);
		} catch (NotEnoughZeroNumbersException exc) {
			System.out.print(exc.getMessage());
		}
	}

}
