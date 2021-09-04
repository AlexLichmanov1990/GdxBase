package base.xlsDB;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XLSDataBase {
    private InputStream xlsFile;
    private Workbook workbook;

    public XLSDataBase(String file) throws IOException {
        this.xlsFile = new FileInputStream(file);
        this.workbook = new HSSFWorkbook(xlsFile);
    }

    public Cell getCell(int list, int row, int col) {
        Sheet sheet = workbook.getSheetAt(list);
        Cell cell = sheet.getRow(row).getCell(col);
        return cell;
    }

    public String getString(int list, int row, int col) {
        Sheet sheet = workbook.getSheetAt(list);
        Cell cell = sheet.getRow(row).getCell(col);
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }

    public Integer getInt(int list, int row, int col) {
        Sheet sheet = workbook.getSheetAt(list);
        Cell cell = sheet.getRow(row).getCell(col);
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return new Integer((int) cell.getNumericCellValue());
        }
        return null;
    }

    public Float getFloat(int list, int row, int col) {
        Sheet sheet = workbook.getSheetAt(list);
        Cell cell = sheet.getRow(row).getCell(col);
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return new Float((float) cell.getNumericCellValue());
        }
        return null;
    }

    public Boolean getBoolean(int list, int row, int col) {
        Sheet sheet = workbook.getSheetAt(list);
        Cell cell = sheet.getRow(row).getCell(col);
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return new Boolean(cell.getBooleanCellValue());
        }
        return null;
    }

    public void close() throws IOException {
        this.xlsFile.close();
    }
}
