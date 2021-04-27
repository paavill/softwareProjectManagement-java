package ru.rsreu.Chistyakov0618.networkHardware;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class NetworkHardwareFileService {

	private NetworkHardwareFileService() {
		
	}
	
	public static void loadNetworkHardwareToFile(NetworkHardware hardware, File file) throws FileNotFoundException {
		OutputStream outputStream = new FileOutputStream(file);
	}
	
}
