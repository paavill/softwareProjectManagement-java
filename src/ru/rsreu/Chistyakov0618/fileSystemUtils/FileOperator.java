package ru.rsreu.Chistyakov0618.fileSystemUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.prutzkow.resourcer.Resourcer;

public class FileOperator {

	private FileOperator() {

	}

	public static ActResult copy(String sourceFilePath, String copyFilePath) throws IOException {
		File sourceFile = new File(sourceFilePath);

		if (!sourceFile.exists()) {
			throw new FileNotFoundException(
					String.format(Resourcer.getString("files.file.exceptions.notFound"), sourceFilePath));
		}
		byte[] buffer = FileOperator.getBytesFromFile(sourceFilePath);
		FileOperator.loanBytesToFile(buffer, copyFilePath);

		return ActResult.COPIED.addElemenPath(sourceFilePath, copyFilePath);
	}

	public static ActResult move(String sourceFilePath, String targetFilePath) throws IOException {
		FileOperator.copy(sourceFilePath, targetFilePath);
		File f = new File(sourceFilePath);
		try {
			f.delete();
			return ActResult.MOVED.addElemenPath(sourceFilePath, targetFilePath);
		} catch (SecurityException e) {
			throw new SecurityException(
					String.format(Resourcer.getString("files.file.exceptions.noAccess"), sourceFilePath));
		}
	}

	private static byte[] getBytesFromFile(String filePath) throws IOException {
		InputStream source = null;
		byte[] buffer = new byte[(int) new File(filePath).length()];
		try {
			source = new FileInputStream(filePath);
			source.read(buffer);
		} catch (SecurityException e) {
			throw new SecurityException(String.format(Resourcer.getString("files.file.exceptions.noAccess"), filePath));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					String.format(Resourcer.getString("files.file.exceptions.cantOpen"), filePath));
		} finally {
			if (source != null) {
				source.close();
			}
		}
		return buffer;
	}

	private static void loanBytesToFile(byte[] buffer, String filePath) throws IOException {
		OutputStream copy = null;
		try {
			copy = new FileOutputStream(filePath);
			copy.write(buffer);
		} catch (SecurityException e) {
			throw new SecurityException(String.format(Resourcer.getString("files.file.exceptions.noAccess"), filePath));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					String.format(Resourcer.getString("files.file.exceptions.cantOpen"), filePath));
		} finally {
			if (copy != null) {
				copy.close();
			}
		}
	}
}
