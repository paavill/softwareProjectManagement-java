package ru.rsreu.Chistyakov0618.fileSystemUtils;

import java.io.File;

import com.prutzkow.resourcer.Resourcer;

public class FolderCreator {

	private FolderCreator() {

	}

	private static boolean create(String folderPath) {
		File f = new File(folderPath);
		try {
			boolean executed = f.mkdirs();
			return executed;
		} catch (SecurityException e) {
			throw new SecurityException(Resourcer.getString("files.folder.exceptions.noAccess"));
		}
	}

	private static boolean exists(String folderPath) {
		File f = new File(folderPath);
		try {
			return f.exists();
		} catch (SecurityException e) {
			throw new SecurityException(Resourcer.getString("files.folder.exceptions.noAccess"));
		}
	}

	private static String getFullPath(String folderPath) {
		File f = new File(folderPath);
		try {
			return f.getAbsolutePath();
		} catch (SecurityException e) {
			throw new SecurityException(Resourcer.getString("files.folder.exceptions.noAccess"));
		}
	}

	public static ActResult createFolder(String folderPath) throws SecurityException {
		ActResult result;
		if (!FolderCreator.exists(folderPath)) {
			if (FolderCreator.create(folderPath)) {
				result = ActResult.CREATED;
			} else {
				result = ActResult.NOT_CREATED;
			}
		} else {
			result = ActResult.EXISTS;
		}
		
		return result.addElemenPath(FolderCreator.getFullPath(folderPath));
	}
}
