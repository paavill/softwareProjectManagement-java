package ru.rsreu.Chistyakov0618.fileSystemUtils;

import java.io.File;

public class FolderCreator {

	private FolderCreator() {

	}

	public static boolean createFolder(String folderPath) {
		File f = new File(folderPath);
		boolean executed = f.mkdirs();
		return executed;
	}

	public static boolean exists(String folderPath) {
		File f = new File(folderPath);
		return f.exists();
	}

	public static String getFullFolderPath(String folderPath) {
		File f = new File(folderPath);
		return f.getAbsolutePath();
	}
}
