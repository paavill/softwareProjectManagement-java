package ru.rsreu.Chistyakov0618;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0618.fileSystemUtils.ActResult;
import ru.rsreu.Chistyakov0618.fileSystemUtils.FolderCreator;
import ru.rsreu.Chistyakov0618.fileSystemUtils.PathFormer;
import ru.rsreu.Chistyakov0618.fileSystemUtils.FileCreator;
import ru.rsreu.Chistyakov0618.fileSystemUtils.FileOperator;
import ru.rsreu.Chistyakov0618.networkHardware.NetworkHardware;
import ru.rsreu.Chistyakov0618.networkHardware.NetworkHardwareFileService;
import ru.rsreu.Chistyakov0618.networkHardware.NetworkHardwareInitializer;
import ru.rsreu.Chistyakov0618.networkHardware.NetworkHardwareService;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	/**
	 * Entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		StringBuilder message = new StringBuilder();
		ActResult createResult;
		/** Generating folder and file paths **/
		String sourceFolderPath = PathFormer.getFormatedPath(Resourcer.getString("files.folder.source.name"));
		String moveFolderPath = PathFormer.getFormatedPath(Resourcer.getString("files.folder.move.name"));
		String copyFolderPath = PathFormer.getFormatedPath(moveFolderPath,
				Resourcer.getString("files.folder.copy.name"));
		String dataFilePath = PathFormer.getFormatedPath(sourceFolderPath, Resourcer.getString("files.file.data.name"));
		String dataCopyFilePath = PathFormer.getFormatedPath(copyFolderPath,
				Resourcer.getString("files.file.data.name") + Resourcer.getString("files.file.backup.extension"));
		String dataMoveFilePath = PathFormer.getFormatedPath(moveFolderPath,
				Resourcer.getString("files.file.data.name"));
		/** Data initialization **/
		NetworkHardware[] networkHardware = NetworkHardwareInitializer.initializeNetworkHardwares();

		try {
			/** Create folders **/
			createResult = FolderCreator.createFolder(sourceFolderPath);
			message.append(Resourcer.getString("files.folder")).append(" ").append(createResult.getResultDescription());
			System.out.println(message);
			message.setLength(0);

			createResult = FolderCreator.createFolder(moveFolderPath);
			message.append(Resourcer.getString("files.folder")).append(" ").append(createResult.getResultDescription());
			System.out.println(message);
			message.setLength(0);

			createResult = FolderCreator.createFolder(copyFolderPath);
			message.append(Resourcer.getString("files.folder")).append(" ").append(createResult.getResultDescription());
			System.out.println(message);
			message.setLength(0);

			/** Creating a file in the Source folder **/
			createResult = FileCreator.createNewFile(dataFilePath);
			message.append(Resourcer.getString("files.file")).append(" ").append(createResult.getResultDescription());
			System.out.println(message);
			message.setLength(0);

			/** Loading data into a file **/
			NetworkHardwareFileService.loadNetworkHardwareToFile(networkHardware, dataFilePath);

			/** Copying a file **/
			createResult = FileOperator.copy(dataFilePath, dataCopyFilePath);
			message.append(Resourcer.getString("files.file")).append(" ").append(createResult.getResultDescription());
			System.out.println(message);
			message.setLength(0);

			System.out.println(String.format(Resourcer.getString("question.move"), dataFilePath, dataCopyFilePath));
			Scanner console = new Scanner(System.in);

			/** Moving a file **/
			if (getBooleanByUserDecision(console.nextLine())) {
				createResult = FileOperator.move(dataFilePath, dataMoveFilePath);
				message.append(Resourcer.getString("files.file")).append(" ")
						.append(createResult.getResultDescription());
				System.out.println(message);
				message.setLength(0);
			}
			console.close();

			/** Loading data from file **/
			NetworkHardware[] networkHardwareCopy = NetworkHardwareFileService
					.loanNetworkHardwareFromFile(dataCopyFilePath);
			/** Loading data from file **/
			NetworkHardware[] networkHardwareMove = NetworkHardwareFileService
					.loanNetworkHardwareFromFile(dataMoveFilePath);

			/** Arrays output **/
			message.append(Resourcer.getString("files.folder.source.name")).append("\n")
					.append(NetworkHardwareService.getFormatedTable(networkHardware));
			System.out.println(message);
			message.setLength(0);

			message.append(Resourcer.getString("files.folder.copy.name")).append("\n")
					.append(NetworkHardwareService.getFormatedTable(networkHardwareCopy));
			System.out.println(message);
			message.setLength(0);

			message.append(Resourcer.getString("files.folder.move.name")).append("\n")
					.append(NetworkHardwareService.getFormatedTable(networkHardwareMove));
			System.out.println(message);
			message.setLength(0);

			message.append(String.format(Resourcer.getString("message.compateWith"),
					Resourcer.getString("files.folder.source.name"), Resourcer.getString("files.folder.move.name")))
					.append("\n");
			/** Comparing **/
			message.append(NetworkHardwareService.parceCompareArrayToTable(
					NetworkHardwareService.compareElementsArrays(networkHardware, networkHardwareMove))).append("\n");
			System.out.println(message);
			message.setLength(0);

			message.append(String.format(Resourcer.getString("message.compateWith"),
					Resourcer.getString("files.folder.source.name"), Resourcer.getString("files.folder.copy.name")))
					.append("\n");

			message.append(NetworkHardwareService.parceCompareArrayToTable(
					NetworkHardwareService.compareElementsArrays(networkHardware, networkHardwareCopy))).append("\n");
			System.out.println(message);
			message.setLength(0);

		} catch (IOException | ClassNotFoundException | SecurityException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private static boolean getBooleanByUserDecision(String decision) throws IllegalArgumentException {
		String userDecision = decision.toLowerCase();
		if (userDecision.equals(Resourcer.getString("question.move.answer.yes"))) {
			return true;
		} else if (userDecision.equals(Resourcer.getString("question.move.answer.no"))) {
			return false;
		} else {
			throw new IllegalArgumentException(String.format(Resourcer.getString("question.move.exception"), decision));
		}
	}
}
