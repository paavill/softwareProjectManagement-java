package ru.rsreu.Chistyakov0618.fileSystemUtils;

import java.io.File;
import java.io.IOException;

import com.prutzkow.resourcer.Resourcer;

public class FileCreator {

	private FileCreator() {

	}

	private static boolean create(String filePath) throws IOException {
		File f = new File(filePath);
		try {
			boolean executed = f.createNewFile();
			return executed;
		} catch (SecurityException e) {
			throw new SecurityException(String.format(Resourcer.getString("files.file.exceptions.noAccess"), filePath));
		} catch (IOException e) {
			throw new IOException(String.format(Resourcer.getString("files.file.exceptions.streamIOError"), filePath));
		}
	}

	private static boolean exists(String filePath) {
		File f = new File(filePath);
		try {
			return f.exists();
		} catch (SecurityException e) {
			throw new SecurityException(String.format(Resourcer.getString("files.file.exceptions.noAccess"), filePath));
		}
	}

	private static String getFullPath(String folderPath) {
		File f = new File(folderPath);
		try {
			return f.getAbsolutePath();
		} catch (SecurityException e) {
			throw new SecurityException(Resourcer.getString("files.file.exceptions.noAccess"));
		}
	}

	public static ActResult createNewFile(String filePath) throws IOException {
		ActResult result;
		if (!FileCreator.exists(filePath)) {
			if (FileCreator.create(filePath)) {
				result = ActResult.CREATED;
			} else {
				result = ActResult.NOT_CREATED;
			}
		} else {
			result = ActResult.EXISTS;
		}

		return result.addElemenPath(FileCreator.getFullPath(filePath));
	}

}
