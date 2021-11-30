package com.example.newtest.rest;


import com.example.newtest.config.JdbcConnection;
import com.example.newtest.model.Todo;
import com.example.newtest.repository.TodoRepository;
import com.example.newtest.service.TodoService;
import com.example.newtest.utility.ExcelSheetGenerator;
import com.example.newtest.utility.StaticData;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/todos")
public class TodoResource {

    private TodoRepository todoRepository;

    private TodoService todoService;

    public TodoResource(){
        this.todoRepository = new TodoRepository();
        this.todoService = new TodoService();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Todo> getJson(){
        List<Todo> todos = null;
        try {
            todos = todoRepository.getAllTodos();
            return todos;
        } catch (SQLException e) {
            e.printStackTrace();
            return todos;
        }

    }


    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Todo getTodo(@PathParam("id") int id){
        System.out.println("Todo id : " + id);
        Todo todo = null;
        try {
            todo = todoService.getTodo(id);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Path("/{id}/pdf")
    @GET
    public Response generateTodoPdf(@PathParam("id") int id){
         todoService.generatePdfFromTodo(id);
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createTodo(Todo todo)  {
        System.out.println(todo);
        try {
            todoRepository.createTodo(todo);
            return Response.status(Response.Status.CREATED).entity (todo).build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @Path("/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateTodo(Todo todo, @PathParam("id") int id ){
        System.out.println(todo);
        try {
            todoRepository.updateTodo(id,todo);
            return Response.status(Response.Status.CREATED).entity(todo).build();
        }catch (SQLException | NoSuchFieldException | IllegalAccessException throwables) {
            throwables.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(throwables.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteTodo (@PathParam("id") int id){
        try {
            todoRepository.deleteTodo(id);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }

    @Path("/pdf")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public  Response  generateTable ()  {
        Document my_report = new Document();
        try {
              PdfWriter.getInstance(my_report,
                      new FileOutputStream(StaticData.staticDir + "/pdfs/"+ "my_report" + new Date().getSeconds() +
                              ".pdf"));

            my_report.open();
            PdfPTable report_table = new PdfPTable(3);
            PdfPCell table_cell ;

            PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement("select * from todos");

            ResultSet resultSet =   preparedStatement.executeQuery();

            while (resultSet.next()){

                String id = resultSet.getString("id");
                table_cell = new PdfPCell(new Phrase(id));
                report_table.addCell(table_cell);


                String summary = resultSet.getString("summary");
                table_cell = new PdfPCell(new Phrase(summary));
                report_table.addCell(table_cell);


                String description = resultSet.getString("description");
                table_cell = new PdfPCell(new Phrase(description));
                report_table.addCell(table_cell);
            }

            my_report.add(report_table);
            my_report.close();

            resultSet.close();
            return Response.status(Response.Status.CREATED).entity( "Pdf report generated at : " +  StaticData.staticDir + "/pdfs/").build();
        } catch (DocumentException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
            return  Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }

    }

    @Path("/sheet")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response generateSheet() {
        try {
            ResultSet result = todoRepository.getTodos();
            ExcelSheetGenerator.generate(result);
            return Response.status(Response.Status.CREATED).entity( "Excel sheet generated in : " +  ExcelSheetGenerator.excelSheetDir).build();
        } catch (SQLException | NoSuchFieldException | IOException | IllegalAccessException e) {
            e.printStackTrace();
            return  Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }

    }

}
