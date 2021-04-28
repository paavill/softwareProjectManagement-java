package ru.rsreu.Chistyakov0618.networkHardware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.prutzkow.resourcer.Resourcer;

public class NetworkHardwareFileService {

	private NetworkHardwareFileService() {

	}

	public static void loadNetworkHardwareToFile(NetworkHardware[] hardware, String filePath) throws IOException {
		File file = new File(filePath);
		OutputStream outputStream = null;
		ObjectOutputStream objectOutput = null;
		try {
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			outputStream = new FileOutputStream(filePath, true);
			objectOutput = new ObjectOutputStream(outputStream);
			objectOutput.writeObject(hardware);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					String.format(Resourcer.getString("files.file.exceptions.notFound"), filePath));
		} catch (IOException e) {
			throw new IOException(String.format(Resourcer.getString("files.file.exceptions.streamIOError"), filePath));
		} finally {
			if (objectOutput != null) {
				objectOutput.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static NetworkHardware[] loanNetworkHardwareFromFile(String filePath)
			throws ClassNotFoundException, IOException {
		NetworkHardware[] result = null;
		File file = new File(filePath);
		InputStream inputStream = null;
		ObjectInputStream objectInput = null;
		try {
			inputStream = new FileInputStream(file);
			objectInput = new ObjectInputStream(inputStream);
			result = (NetworkHardware[]) objectInput.readObject();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(
					String.format(Resourcer.getString("files.file.exceptions.notContainsData"), filePath));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					String.format(Resourcer.getString("files.file.exceptions.notFound"), filePath));
		} catch (IOException e) {
			throw new IOException(String.format(Resourcer.getString("files.file.exceptions.streamIOError"), filePath));
		} finally {
			if (objectInput != null) {
				objectInput.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return result;
	}
}
