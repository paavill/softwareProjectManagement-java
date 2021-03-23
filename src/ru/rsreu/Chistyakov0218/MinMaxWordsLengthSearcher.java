package ru.rsreu.Chistyakov0218;

import java.util.function.BiPredicate;

/**
 * A utility class that provide the method that returns words of maximum and
 * minimum length from a string
 * 
 * @author Chistyakov Pavel
 *
 */
public class MinMaxWordsLengthSearcher {

	private MinMaxWordsLengthSearcher() {

	}

	/**
	 * Compares values maxValue and checkingValue and returns true if checkingValue
	 * bigger maxValue
	 * 
	 * @param maxValue      - the value to compare against
	 * @param checkingValue - checking value
	 * @return Returns boolean value
	 */
	private static boolean isBiggerPredicate(int maxValue, int checkingValue) {
		return checkingValue > maxValue;
	}

	/**
	 * Compares values minValue and checkingValue and returns true if checkingValue
	 * lesser minValue
	 * 
	 * @param minValue      - the value to check against
	 * @param checkingValue - checking value
	 * @return Returns boolean value
	 */
	private static boolean isLesserPredicate(int minValue, int checkingValue) {
		return checkingValue < minValue;
	}

	/**
	 * Selects words matching the length parameter
	 * 
	 * @param selectionParameter - parameter by which words are selected
	 * @param words              - set of words the length to be processed
	 * @return Returns a set of words matching a parameter
	 */
	private static String selectWords(int selectionParameter, String[] words) {
		String wordsResult = "";

		for (String i : words) {
			if (i.length() == selectionParameter && !wordsResult.contains(i)) {
				wordsResult += " " + i;
			}
		}

		return wordsResult;
	}

	/**
	 * Finds the length of words matching a predicate
	 * 
	 * @param words     - set of words the length to be processed
	 * @param predicate - selection condition
	 * @return Returns - matching values
	 */
	private static int getWordsLengthByPredicate(String[] words, BiPredicate<Integer, Integer> predicate) {
		int result = words[0].length();

		for (String i : words) {
			if (predicate.test(result, i.length())) {
				result = i.length();
			}
		}

		return result;
	}

	/**
	 * Splits a string into words and returns words of maximum and minimum length
	 * 
	 * @param string - processed string
	 * @return Returns words of maximum and minimum length
	 */
	static String getWords(String string) throws InvalidStringExceptions {
		if (string.isEmpty()) {
			throw new InvalidStringExceptions(StringExceptionCodes.EMPTY, "Exception: String is empty.");
		}
		string = string.trim();
		String[] words = string.split("\\s+");
		if (!string.contains(" ")) {
			throw new InvalidStringExceptions(StringExceptionCodes.NO_SPACES,
					"Exception: String does not contain any spaces between words.");
		}

		int minLengthWord = MinMaxWordsLengthSearcher.getWordsLengthByPredicate(words,
				MinMaxWordsLengthSearcher::isLesserPredicate);
		int maxLengthWord = MinMaxWordsLengthSearcher.getWordsLengthByPredicate(words,
				MinMaxWordsLengthSearcher::isBiggerPredicate);

		String result = MinMaxWordsLengthSearcher.selectWords(minLengthWord, words)
				+ MinMaxWordsLengthSearcher.selectWords(maxLengthWord, words);

		return result;
	}
}
