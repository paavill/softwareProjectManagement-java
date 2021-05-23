package ru.rsreu.Chistyakov0818;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.prutzkow.resourcer.Resourcer;

public class CollectionToTableFormatter {

	private CollectionToTableFormatter() {

	}

	public static String format(Collection<?> elements) throws InstantiationException, IllegalAccessException {
		StringBuilder result = new StringBuilder();
		String format = null;
		String horisontalDelimiter = null;
		String verticalDelimiter = Resourcer.getString("table.verticalDelimiter");
		int[] maxColumnsWidths = null;
		Object[] columnNames = null;
		if (!elements.isEmpty()) {
			maxColumnsWidths = getMaxColomnWidthes(elements);
			format = getFormat(maxColumnsWidths, verticalDelimiter);
			columnNames = getColumnsNames(elements.toArray()[0].getClass());
			String title = String.format(format.toString(), columnNames);
			String horizontalDelimiterPiece = Resourcer.getString("table.horizontalDelimiterPiece");
			horisontalDelimiter = new String(new char[title.length()]).replace('\0',
					horizontalDelimiterPiece.charAt(0));
			result.append(horisontalDelimiter).append("\n");
			result.append(title).append("\n");
			result.append(horisontalDelimiter).append("\n");
		}
		Iterator<?> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			Object[] columnValues = getStringValues(o);
			result.append(String.format(format, columnValues)).append("\n");
		}
		if (!elements.isEmpty()) {
			result.append(horisontalDelimiter);
		}
		return result.toString();
	}

	private static Object[] getColumnsNames(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		Object[] columnNames = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			columnNames[i] = fields[i].getName().toUpperCase();
		}
		return columnNames;
	}

	private static String getFormat(int[] maxColumnsWidths, String delimiter) {
		StringBuilder format = new StringBuilder();
		for (int width : maxColumnsWidths) {
			format.append(delimiter).append("%").append(width).append("s");
		}
		format.append(delimiter);
		return format.toString();
	}

	private static String[] getStringValues(Object element) throws IllegalArgumentException, IllegalAccessException {
		Class<?> formatedElementClass = element.getClass();
		Field[] fields = formatedElementClass.getDeclaredFields();
		int columnsNumber = fields.length;
		String[] columnValues = new String[columnsNumber];
		for (Field f : fields) {
			f.setAccessible(true);
		}
		for (int i = 0; i < fields.length; i++) {
			columnValues[i] = String.valueOf(fields[i].get(element));
		}
		return columnValues;
	}

	private static int[] getMaxColomnWidthes(Collection<?> elements)
			throws IllegalArgumentException, IllegalAccessException {
		int columnsNumber = 0;
		Object[] columnNames = null;
		int[] maxColumnsWidths = null;
		if (!elements.isEmpty()) {
			columnsNumber = elements.toArray()[0].getClass().getDeclaredFields().length;
			columnNames = getColumnsNames(elements.toArray()[0].getClass());
			maxColumnsWidths = new int[columnsNumber];
			Arrays.fill(maxColumnsWidths, 0);
			updateMaxColumnsWidths(maxColumnsWidths, columnNames);
		}
		Iterator<?> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			Object[] columnValues = getStringValues(o);
			updateMaxColumnsWidths(maxColumnsWidths, columnValues);
		}
		return maxColumnsWidths;
	}

	private static void updateMaxColumnsWidths(int[] maxColumnsWidths, Object[] columnValues) {
		for (int i = 0; i < maxColumnsWidths.length; i++) {
			int colomnWidth = columnValues[i].toString().length();
			if (maxColumnsWidths[i] < colomnWidth) {
				maxColumnsWidths[i] = colomnWidth;
			}
		}
	}
}
