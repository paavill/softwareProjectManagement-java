package ru.rsreu.Chistyakov0618;

import java.util.Arrays;
import java.util.Locale;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0618.fileSystemUtils.FolderCreator;
import ru.rsreu.Chistyakov0618.networkHardware.NetworkHardware;
import ru.rsreu.Chistyakov0618.networkHardware.NetworkHardwareInitializer;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		StringBuilder message = new StringBuilder();
		
		String sourceFolderPath = PathFormer.getFormatedPath(Resourcer.getString("files.folder.source.name"));
		String moveFolderPath = PathFormer.getFormatedPath(Resourcer.getString("files.folder.move.name"));
		String copyFolderPath = PathFormer.getFormatedPath(moveFolderPath, Resourcer.getString("files.folder.copy.name"));
		
		if (!FolderCreator.exists(sourceFolderPath)) {
			if (FolderCreator.createFolder(sourceFolderPath)) {
				message.append(String.format(Resourcer.getString("messages.folder.create.success"),
						FolderCreator.getFullFolderPath(sourceFolderPath)));
			} else {
				message.append(String.format(Resourcer.getString("messages.folder.create.notCreated"),
						FolderCreator.getFullFolderPath(sourceFolderPath)));
			}
		} else {
			message.append(String.format(Resourcer.getString("messages.folder.create.exists"),
					FolderCreator.getFullFolderPath(sourceFolderPath)));
		}
		System.out.println(message);
		message.setLength(0);
		
		if (!FolderCreator.exists(moveFolderPath)) {
			if (FolderCreator.createFolder(moveFolderPath)) {
				message.append(String.format(Resourcer.getString("messages.folder.create.success"),
						FolderCreator.getFullFolderPath(moveFolderPath)));
			} else {
				message.append(String.format(Resourcer.getString("messages.folder.create.notCreated"),
						FolderCreator.getFullFolderPath(moveFolderPath)));
			}
		} else {
			message.append(String.format(Resourcer.getString("messages.folder.create.exists"),
					FolderCreator.getFullFolderPath(moveFolderPath)));
		}
		System.out.println(message);
		message.setLength(0);
		
		if (!FolderCreator.exists(copyFolderPath)) {
			if (FolderCreator.createFolder(copyFolderPath)) {
				message.append(String.format(Resourcer.getString("messages.folder.create.success"),
						FolderCreator.getFullFolderPath(copyFolderPath)));
			} else {
				message.append(String.format(Resourcer.getString("messages.folder.create.notCreated"),
						FolderCreator.getFullFolderPath(copyFolderPath)));
			}
		} else {
			message.append(String.format(Resourcer.getString("messages.folder.create.exists"),
					FolderCreator.getFullFolderPath(copyFolderPath)));
		}
		System.out.println(message);
		message.setLength(0);

		NetworkHardware[] networkHardware = NetworkHardwareInitializer.initializeNetworkHardwares();		
		
		for (NetworkHardware i : networkHardware) {
			System.out.println(i.toString());
		}
		Arrays.sort(networkHardware);
		System.out.println(PathFormer.getFormatedPath("Source", "Move"));
		if (FolderCreator.createFolder(PathFormer.getFormatedPath("Source"))) {
			System.out.println("!");
		}
		System.out.println("\n");
		for (NetworkHardware i : networkHardware) {
			System.out.println(i.toString());
		}
	}
}
