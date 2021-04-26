package ru.rsreu.Chistyakov0618;

import java.io.File;

public class FolsersCreator {

	private FolsersCreator() {
		
	}
	
	public static boolean createFolder(String folderPath) {
		File f = new File(folderPath);
		boolean executed = f.mkdirs();
		return executed;
	}
	
	//public static boolean createFolder
}
