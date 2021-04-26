package ru.rsreu.Chistyakov0618;

import java.util.Arrays;
import java.util.Locale;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		NetworkHardware[] t = NetworkHardwareInitializer.initializeNetworkHardwares();
		for (NetworkHardware i : t) {
			System.out.println(i.toString());
		}
		Arrays.sort(t);
		System.out.println(PathFormer.getFormatedPath("Source", "Move"));
		if (FolsersCreator.createFolder(PathFormer.getFormatedPath("Source"))) {
			System.out.println("!");
		}
		System.out.println("\n");
		for (NetworkHardware i : t) {
			System.out.println(i.toString());
		}
	}
}
