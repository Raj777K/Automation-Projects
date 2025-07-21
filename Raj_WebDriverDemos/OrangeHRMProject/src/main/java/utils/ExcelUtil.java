package utils;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] readExcel(String filePath, String sheetName) {
        Object[][] data = null;

        try (InputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING); // force as string
                        data[i - 1][j] = cell.getStringCellValue().trim(); // ✅ trim spaces
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
