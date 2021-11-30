package com.example.newtest.utility;
import com.example.newtest.model.Todo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelSheetGenerator {
   public static String excelSheetDir = StaticData.staticDir + "/sheets/";

    public static void generate(ResultSet result) throws SQLException, NoSuchFieldException, IllegalAccessException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Todos");
        List<String> fields = getFields(result);
        writeHeaderLines(sheet,fields);
        writeDataLines(result,sheet);
        FileOutputStream outputStream = new FileOutputStream(excelSheetDir + "/todos_" + new Date().getSeconds()+".xlsx");
        workbook.write(outputStream);
        workbook.close();
    }

    private static List<String> getFields(ResultSet result) throws SQLException {
        List<String> columns = new ArrayList();
        int columnCount =  result.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columns.add(result.getMetaData().getColumnName(i));
        }
        return columns;
    }

    private static void writeHeaderLines(XSSFSheet sheet, List<String> fields) throws SQLException {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.size(); i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(fields.get(i));
        }
    }

    private static void writeDataLines(ResultSet result, XSSFSheet sheet
    ) throws SQLException, NoSuchFieldException, IllegalAccessException {
        int rows = 1;
        int columnCount =  result.getMetaData().getColumnCount();
       while(result.next()){
           Row row = sheet.createRow(rows++);
           for (int i = 1; i <= columnCount; i++) {
               String fieldName = result.getMetaData().getColumnName(i);
               Cell cell = row.createCell(i);
               String dataType = result.getMetaData().getColumnTypeName(i);
               if(dataType == "INT UNSIGNED")
                 cell.setCellValue(result.getInt(fieldName));
               else if(dataType =="VARCHAR" ){
                   cell.setCellValue(result.getString(fieldName));
               }else if(dataType == "TIMESTAMP"){
                   cell.setCellValue(result.getTimestamp(fieldName));
               }
           }
       }
    }


}
