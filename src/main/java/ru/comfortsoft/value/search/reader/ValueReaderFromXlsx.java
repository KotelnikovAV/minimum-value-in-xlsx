package ru.comfortsoft.value.search.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.comfortsoft.value.exception.NotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("xlsxValueReader")
public class ValueReaderFromXlsx implements ValueReader {

    @Override
    public int[] readValues(String path) {
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    double value = cell.getNumericCellValue();

                    if (value % 1 != 0) {
                        throw new IllegalArgumentException("Non-integer value in cell " + cell.getAddress());
                    }

                    numbers.add((int) value);
                }
            }
        } catch (IOException e) {
            throw new NotFoundException("File on the path " + path + " does not exist");
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
