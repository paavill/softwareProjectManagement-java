package ru.rsreu.Chistyakov0218;

import java.util.Scanner;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	/**
	 * Entry point
	 * 
	 * @param args - Launch parameters
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.print("Enter number of strings: n = ");
		int stringCount = Integer.parseInt(in.next());
		in.nextLine();

		String[] stringArray = new String[stringCount];
		for (int i = 0; i < stringArray.length; i++) {
			System.out.println("Enter " + (i + 1) + ". string: ");
			stringArray[i] = in.nextLine();
		}

		in.close();

		for (int i = 0; i < stringArray.length; i++) {
			try {
				System.out.println("Resul for " + (i + 1) + ". string:\n" + "Minimum and maximum length words:"
						+ MinMaxWordsLengthSearcher.getWords(stringArray[i]));
			} catch (InvalidStringExceptions exception) {
				System.out.println(exception.getMessage() + "\n" + "Exception code: " + exception.getErrorCode());
			}
		}

	}

}
