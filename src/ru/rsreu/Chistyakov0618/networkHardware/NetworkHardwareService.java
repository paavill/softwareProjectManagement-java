package ru.rsreu.Chistyakov0618.networkHardware;

import com.prutzkow.resourcer.Resourcer;

public class NetworkHardwareService {

	private NetworkHardwareService() {

	}

	public static String getFormatedTable(NetworkHardware... hardware) {
		StringBuilder result = new StringBuilder(Resourcer.getString("table.lines.high"));
		result.append("\n");
		result.append(String.format(Resourcer.getString("table.data.format"), Resourcer.getString("table.mac"),
				Resourcer.getString("table.description"), Resourcer.getString("table.type"))).append("\n");
		result.append(Resourcer.getString("table.lines.high")).append("\n");
		for (NetworkHardware i : hardware) {
			result.append(String.format(Resourcer.getString("table.data.format"), i.getMacAdress(), i.getDescription(),
					i.getType().toString())).append("\n");
		}
		result.append(Resourcer.getString("table.lines.high")).append("\n");
		return result.toString();
	}

	public static boolean[] compareElementsArrays(NetworkHardware[] firstArray, NetworkHardware[] secondArray) {
		boolean[] result = new boolean[firstArray.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = firstArray[i].equals(secondArray[i]);
		}
		return result;
	}

	public static String parceCompareArrayToTable(boolean[] compareArray) {
		StringBuilder result = new StringBuilder(String.format(Resourcer.getString("table.equal.format.head"),
				Resourcer.getString("table.element"), Resourcer.getString("table.compare")));
		result.append("\n");
		String compareText;
		for (int i = 1; i <= compareArray.length; i++) {
			if (compareArray[i - 1]) {
				compareText = Resourcer.getString("element.equal");
			} else {
				compareText = Resourcer.getString("element.noEqual");
			}
			result.append(String.format(Resourcer.getString("table.equal.format"), i, compareText)).append("\n");
		}
		return result.toString();
	}
}
