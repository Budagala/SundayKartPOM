package com.alt.sundaykart.automation.utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alt.sundaykart.automation.base.Constants;


public class ExcelUtilities {

	private static XSSFWorkbook excelWorkbook;
	private static XSSFSheet excelSheet;
	private static XSSFCell excelCell;
	private static XSSFCell writeCell;
	private static XSSFRow writeRow;

	public static void setExcelFile(String path, String sheetName) {
		try {

			Log.info("Set the Excel path in set ExcelFile path:" + path + ":SheetName:" + sheetName);
			FileInputStream fis = new FileInputStream(path);
			excelWorkbook = new XSSFWorkbook(fis);
		} catch (Exception e) {

			Log.error("Class Utils | Method setExcelFile | Exception desc : " + e.getMessage());
		}

	}

	public static String getCellData(int row, int col, String sheetName) {

		String cellData = "";
		try {

			// Log.info("In the Get Cell Data method of ExcelUtilies Class for
			// row:" + row + ",Col:" + col + "& in sheet:"+ sheetName);
			DataFormatter formatter = new DataFormatter();
			excelSheet = excelWorkbook.getSheet(sheetName);
			excelCell = excelSheet.getRow(row).getCell(col);
			switch (excelCell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellData = excelCell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				cellData = formatter.formatCellValue(excelCell);
				break;

			}

			return cellData;

		} catch (Exception e) {

			Log.error("Class Utils | Method getCellData | Exception desc : " + e.getMessage());
			return cellData;
		}

	}

	public static int getRowCount(String sheetName) {
		int rowCount = 0;
		try {
			// Log.info("In the getRowCount method of ExcelUtilites Class for
			// Sheet:" + sheetName);
			excelSheet = excelWorkbook.getSheet(sheetName);
			rowCount = excelSheet.getLastRowNum() + 1;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowCount | Exception desc : " + e.getMessage());
		}
		return rowCount;
	}

	public static int getRowContains(String sTestCaseName, int colNum, String sheetName) {

		int testCaseRowStart = 0;
		try {

			// Log.info("In the getRowContains method of ExcelUtilites Method
			// for test case:" + sheetName + ": in the sheet:" + sheetName);
			excelSheet = excelWorkbook.getSheet(sheetName);
			int rowCount = getRowCount(sheetName);
			for (testCaseRowStart = 0; testCaseRowStart < rowCount; testCaseRowStart++) {

				if (ExcelUtilities.getCellData(testCaseRowStart, colNum, sheetName).equals(sTestCaseName)) {
					break;
				}
			}

		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : " + e.getMessage());

		}
		return testCaseRowStart;

	}

	public static int getTestStepsCount(String sheetName, String sTestCaseID, int iTestCaseStart) {

		int i = 0;
		try {
			excelSheet = excelWorkbook.getSheet(sheetName);
			for (i = iTestCaseStart; i < getRowCount(sheetName); i++) {

				if (getCellData(i, Constants.col_TestCaseId, sheetName) != null
						&& sTestCaseID.equals(getCellData(i, Constants.col_TestCaseId, sheetName))) {

					if (sTestCaseID.equals(getCellData(i, Constants.col_TestCaseId, sheetName)))
						System.out.println("");

				} else {
					break;
				}

			}

		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : " + e.getMessage());

		}
		return i;

	}

	public static void setCellData(String result, int rowNum, int colNum, String sheetName) throws Exception {
		try {

			excelSheet = excelWorkbook.getSheet(sheetName);
			writeRow = excelSheet.getRow(rowNum);
			writeCell = writeRow.getCell(colNum);
			XSSFCellStyle my_style = excelWorkbook.createCellStyle();
			XSSFFont my_font = excelWorkbook.createFont();
			my_font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			my_font.setColor(IndexedColors.WHITE.getIndex());
			if (result.equals("PASS")) {
				my_style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				my_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			} else {
				my_style.setFillForegroundColor(IndexedColors.RED.getIndex());
				my_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			}
			my_style.setFont(my_font);
			if (writeRow == null) {
				writeRow = excelSheet.createRow(rowNum);
			}
			if (writeCell == null) {
				writeCell = writeRow.createCell(colNum);
				if (result.equals("PASS")) {
					writeCell.setCellValue(result);
					writeCell.setCellStyle(my_style);
				} else if (result.equals("FAIL")) {
					writeCell.setCellValue(result);
					writeCell.setCellStyle(my_style);
				} else {
					writeCell.setCellValue(result);
				}
			} else {
				if (result.equals("PASS")) {
					writeCell.setCellValue(result);
					writeCell.setCellStyle(my_style);
				} else if (result.equals("FAIL")) {
					writeCell.setCellValue(result);
					writeCell.setCellStyle(my_style);
				} else {
					writeCell.setCellValue(result);
				}
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constants.testDataFilePath);
			excelWorkbook.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			excelWorkbook = new XSSFWorkbook(new FileInputStream(Constants.testDataFilePath));
		} catch (Exception e) {
			Log.error("Expection occured at SetCellData Method due to :" + e.toString());
			
		}
	}

}
