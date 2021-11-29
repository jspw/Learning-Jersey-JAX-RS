package com.example.newtest.utility;


import com.example.newtest.config.JdbcConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class PdfGenerator {

    static String base = "/home/shifat/Documents/Jax-rs-Jersey/projects/newtest/data/";

   public static void getPdf(String filename, String data) {
        try {
             Document document = new Document();
             PdfWriter writer;
             FileOutputStream file = new FileOutputStream(   base + filename+ "-" +  new Date().getSeconds() + ".pdf");
            writer = PdfWriter.getInstance(document,file );
            document.open();
            document.add(new Paragraph(data));
            document.close();
            writer.close();
            System.out.println("Done");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void generateTable () throws FileNotFoundException, DocumentException, SQLException {
       Document my_report = new Document();
        PdfWriter.getInstance(my_report,new FileOutputStream("my_report" + new Date().getSeconds() + ".pdf"));

        PdfPTable report_table = new PdfPTable(3);
        PdfPCell table_cell ;

        ResultSet resultSet = (ResultSet) JdbcConnection.getConnection().prepareStatement("select * from todos");

        while (resultSet.next()){
            table_cell = new PdfPCell(new Phrase(resultSet.getString("id")));
            report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(resultSet.getString("summary")));
            report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(resultSet.getString("description")));
            report_table.addCell(table_cell);
        }

        my_report.add(report_table);
        my_report.close();

        resultSet.close();

    }

}
