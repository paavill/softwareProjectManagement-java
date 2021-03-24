package ru.rsreu.Chistyakov0308;

import java.util.Arrays;
import java.util.function.Predicate;

import com.prutzkow.resourcer.Resourcer;

/**
 * A utility class that subtracts SUBTRAHEND of negative numbers of a numerical
 * sequence after the ZERO_NUMth zero element
 * 
 * @author Chistyakov Pavel
 *
 */
public class AfterZeroNumberSubtractor {
	/**
	 * The number to be subtracted from negative elements.
	 */
	private static final int SUBTRAHEND = 10;
	/**
	 * The number of the zero element after which the subtraction occurs.
	 */
	private static final int ZERO_NUM = 3;
	/**
	 * Array of processed items.
	 */
	private final int[] numbersSet;

	/**
	 * 
	 * @param args - source array
	 */
	public AfterZeroNumberSubtractor(int... args) {
		this.numbersSet = args;
	}

	/**
	 * The predicate that checks if a checkingValue is negative.
	 * 
	 * @param checkingValue
	 * @return true if negative, false otherwise
	 */
	private static boolean isNegativePredicate(int checkingValue) {
		return checkingValue < 0;
	}

	/**
	 * Searches the index of the ZERO_NUMth zero element of a numeric sequence.
	 * 
	 * @return index
	 * @throws NotEnoughZeroNumbersException
	 */
	private int getZeroNumberIndex() throws NotEnoughZeroNumbersException {
		int zeroValuesCounter = 0;
		int i = 0;
		while (i < this.numbersSet.length && zeroValuesCounter < AfterZeroNumberSubtractor.ZERO_NUM) {
			if (this.numbersSet[i] == 0) {
				zeroValuesCounter++;
			}
			i++;
		}
		if (zeroValuesCounter != AfterZeroNumberSubtractor.ZERO_NUM) {
			throw new NotEnoughZeroNumbersException(Resourcer.getString("exceptions.message.notEnoughZeroNumbers"));
		}
		return i - 1;
	}

	/**
	 * Subtracts SUBTRAHEND from the elements of the sequence that satisfy the
	 * predicate starting at the element with the zeroNumberIndex + 1.
	 * 
	 * @param zeroNumberIndex
	 * @param predicate
	 */
	private void subtractSubtrahendFromElementsByPredicate(int zeroNumberIndex, Predicate<Integer> predicate) {
		for (int i = zeroNumberIndex + 1; i < this.numbersSet.length; i++) {
			if (predicate.test(this.numbersSet[i])) {
				this.numbersSet[i] -= AfterZeroNumberSubtractor.SUBTRAHEND;
			}
		}
	}

	/**
	 * Subtracts SUBTRAHEND of negative numbers of a numerical sequence after the
	 * ZERO_NUMth zero element.
	 * 
	 * @throws NotEnoughZeroNumbersException
	 */
	public void subtractSubtrahendFromNegativeElementsAfterZeroSequentialNumber() throws NotEnoughZeroNumbersException {
		int zeroSequentialNumber = getZeroNumberIndex();
		this.subtractSubtrahendFromElementsByPredicate(zeroSequentialNumber,
				AfterZeroNumberSubtractor::isNegativePredicate);
	}

	/**
	 * Returns the original array as a string sequence
	 */
	@Override
	public String toString() {
		return Arrays.toString(numbersSet);
	}
}
