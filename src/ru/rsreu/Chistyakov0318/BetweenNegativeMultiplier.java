package ru.rsreu.Chistyakov0318;

import java.util.Arrays;
import com.prutzkow.resourcer.Resourcer;

/**
 * A data class that stores an array of int elements and provides a method to
 * multiply elements between the first two negative numbers.
 * 
 * @author Chistyakov Pavel
 *
 */
public class BetweenNegativeMultiplier {
	/**
	 * Missing index constant of previous negative value
	 */
	private static final int NULL_INDEX = -1;
	/**
	 * Array of processed items
	 */
	private final int[] numbersSet;

	/**
	 * 
	 * @param args - source array
	 */
	public BetweenNegativeMultiplier(int... args) {
		this.numbersSet = args;
	}

	/**
	 * The method finds the index of the negative element after the negative element
	 * with index indexOfPrevious. Search interval: (indexOfPrevious,
	 * numbersSet.length).
	 * 
	 * If there is no previous negative value, assign the value NULL_INDEX to the
	 * argument
	 * 
	 * @param indexOfPrevious - index of the previous negative number
	 * @return the index of the next negative number
	 */
	private int getIndexNegativeNumber(int indexOfPrevious) throws InvalidNegativeIndexNumbException {
		int i = indexOfPrevious + 1;
		if (i < this.numbersSet.length) {
			while (i < this.numbersSet.length && this.numbersSet[i] >= 0) {
				i++;
			}
			if (i == this.numbersSet.length) {
				throw new InvalidNegativeIndexNumbException(Resourcer.getString("exceptions.message.lessThenTwo"));
			}
			return i;
		} else {
			throw new InvalidNegativeIndexNumbException(Resourcer.getString("exceptions.message.outOfBounds"));
		}

	}

	/**
	 * Multiplies the values whose indices belong to (firstIndex, secondIndex)
	 * 
	 * @param firstIndex
	 * @param secondIndex
	 * @return multiplication result
	 */
	private int betweenIndicesMultiply(int firstIndex, int secondIndex) {
		int result = 1;
		for (int i = firstIndex + 1; i < secondIndex; i++) {
			result *= this.numbersSet[i];
		}
		return result;
	}

	/**
	 * The method multiplies the numbers between firstNegativeValueIndex and
	 * secondNegativeValueIndex
	 * 
	 * @return The result of the multiplication
	 */
	public int betweenNegativeMultiply() throws InvalidNegativeIndexNumbException {
		int firstNegativeValueIndex = this.getIndexNegativeNumber(BetweenNegativeMultiplier.NULL_INDEX);
		int secondNegativeValueIndex = this.getIndexNegativeNumber(firstNegativeValueIndex);
		if (secondNegativeValueIndex - firstNegativeValueIndex < 2) {
			throw new InvalidNegativeIndexNumbException(Resourcer.getString("exceptions.message.numbersNear"));
		}
		int result = this.betweenIndicesMultiply(firstNegativeValueIndex, secondNegativeValueIndex);
		return result;
	}

	/**
	 * Returns the original array as a string sequence
	 */
	@Override
	public String toString() {
		return Arrays.toString(numbersSet);
	}
}
