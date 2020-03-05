package com.mopro.support.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mopro.support_base.Driver;

public class Excel extends Driver {

	static String fileURL = "C:\\Users\\Einstien Jackson\\Downloads\\automate.xlsx";
	static Workbook workbook;
	static InputStream ips;
	static File fl = new File(fileURL);

	public static Sheet readExcel(String shName) {
		
		Sheet sh;

		getSheet: {
			try {
				Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
				ips = new FileInputStream(fl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String fileExt = fileURL.substring(fileURL.indexOf("."));
			if (fileExt.contentEquals(".xlsx")) {
				try {
					workbook = new XSSFWorkbook(ips); // XML SpreadSheet Format
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (fileExt.contentEquals(".xls")) {
				try {
					workbook = new HSSFWorkbook(ips); // Horrible SpreadSheet Format
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Invalid File Format");
				break getSheet;
			}
		}
		sh = workbook.getSheet(shName);
		return sh;
	}

	public static String getValues(String shName, int row, int col) {

		Sheet sh;
		String opValue;
		int rowNum = row;
		int celNum = col;
		String sheetName = shName;
		sh = readExcel(sheetName);
		if (sh.getRow(rowNum).getCell(celNum) != null) {
			opValue = sh.getRow(rowNum).getCell(celNum).toString();
		} else {
			opValue = "";
		}
		return opValue;
	}
}
