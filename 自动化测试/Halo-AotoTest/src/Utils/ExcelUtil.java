package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {

	public static Object getCellValue(Sheet sheet, int rowIndex, int cellIndex) {
		Object value = null;

		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(cellIndex);
		/*
		 * System.out.println(rowIndex); System.out.println(cellIndex);
		 */

		if (cell.getCellType() == CellType.STRING) {
			value = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			String cellValue = " ";
			short format = cell.getCellStyle().getDataFormat();
			// System.out.println(format);
			SimpleDateFormat sdf = null;
			if (format == 20 || format == 32) {
				sdf = new SimpleDateFormat("HH:mm");
			} else if (format == 14 || format == 31 || format == 57 || format == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value1 = cell.getNumericCellValue();
				Date date = DateUtil.getJavaDate(value1);
				cellValue = sdf.format(date);
			} else if (format == 176) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			} else {// 日期
				// 无效 format也等于176
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			try {
				cellValue = sdf.format(cell.getDateCellValue());// 日期
			} catch (Exception e) {
				try {
					throw new Exception("exception on get date data !".concat(e.toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} finally {
				sdf = null;
			}
			value = cellValue;
		} else if (cell.getCellType() == CellType.NUMERIC) {
			DecimalFormat df = new DecimalFormat("0");
			value = df.format(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			value = cell.getBooleanCellValue();
		} else if (cell == null || cell.toString().trim().equals("")) {
			value = "";
		} else {
			value = cell.toString();
		}

		return value;
	}

	// yyyy-MM-dd HH:mm:ss
	public static Object getCellValue_YMDHMS(Sheet sheet, int rowIndex, int cellIndex) {
		Object value = null;

		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(cellIndex);
		/*
		 * System.out.println(rowIndex); System.out.println(cellIndex);
		 */

		if (cell.getCellType() == CellType.STRING) {
			value = cell.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {
			String cellValue = " ";
			short format = cell.getCellStyle().getDataFormat();
			// System.out.println(format);
			SimpleDateFormat sdf = null;
			if (format == 176) {
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			try {
				cellValue = sdf.format(cell.getDateCellValue());// 日期
			} catch (Exception e) {
				try {
					throw new Exception("exception on get date data !".concat(e.toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} finally {
				sdf = null;
			}
			value = cellValue;
		} else if (cell.getCellType() == CellType.NUMERIC) {
			DecimalFormat df = new DecimalFormat("0");
			value = df.format(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			value = cell.getBooleanCellValue();
		} else if (cell == null || cell.toString().trim().equals("")) {
			value = "";
		} else {
			value = cell.toString();
		}
		return value;
	}

	public static Object[][] readExcel(String path, int sheetIndex) {
		Workbook workbook = null;

		Object[][] data = null;

		try {
			// Apache POI 中用于操作 Excel 2007及以上版本（.xlsx 格
			// 式）的工作簿对象。XSSFWorkbook 继承自 Workbook 接口，
			// 它允许你在内存中创建、读取和修改 Excel 文件。
			workbook = new XSSFWorkbook(new FileInputStream(path));
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			int rowNum = sheet.getPhysicalNumberOfRows();
			System.out.println(rowNum);
			int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println(cellNum);
			data = new Object[rowNum][cellNum];

			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < cellNum; j++) {
					data[i][j] = getCellValue(sheet, i, j);
					System.out.println(i + ":" + data[i][j] + "             " + "类型为:" + (data[i][j]).getClass());
					// System.out.println((data[i][j]).getClass());
				}
			}

			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public static Object[][] readExcel_YMDHMS(String path, int sheetIndex) {
		Workbook workbook = null;
		Object[][] data = null;
		try {
			// Apache POI 中用于操作 Excel 2007及以上版本（.xlsx 格
			// 式）的工作簿对象。XSSFWorkbook 继承自 Workbook 接口，
			// 它允许你在内存中创建、读取和修改 Excel 文件。
			workbook = new XSSFWorkbook(new FileInputStream(path));
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			int rowNum = sheet.getPhysicalNumberOfRows();
			System.out.println(rowNum);
			int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println(cellNum);
			data = new Object[rowNum][cellNum];
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < cellNum; j++) {
					data[i][j] = getCellValue_YMDHMS(sheet, i, j);
					System.out.println(i + ":" + data[i][j] + "             " + "类型为:" + (data[i][j]).getClass());
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

/*	public static void main(String[] args) {
		// readExcel("resources/passwordData.xlsx", 0);
//		readExcel("resources/StuPunchCardData.xlsx", 0);
//		readExcel("resources/stuSettingData.xlsx", 0);
		readExcel("resources/RegisterData.xlsx", 0);
		// readExcel("resources/StuWeeklyPaperAddData.xlsx", 0);
	}
*/
}
