package ru.rsreu.Chistyakov0618;

import java.io.File;

public class PathFormer {

	private PathFormer() {

	}

	public static String getFormatedPath(String... fileSystemObjects) {
		String path = String.join(File.separator, fileSystemObjects);
		return path;
	}

}
