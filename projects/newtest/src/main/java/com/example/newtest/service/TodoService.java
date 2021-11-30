package com.example.newtest.service;

import com.example.newtest.model.Todo;
import com.example.newtest.model.mapper.ResultSetToTodo;
import com.example.newtest.repository.TodoRepository;
import com.example.newtest.utility.ExcelSheetGenerator;
import com.example.newtest.utility.PdfGenerator;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TodoService {
    private TodoRepository todoRepository = new TodoRepository();


    public Todo  getTodo (int id) throws SQLException, NoSuchFieldException, IllegalAccessException {
      return ResultSetToTodo.todoModel(todoRepository.getTodo(id));
    }


    public List<Todo> getTodos() throws SQLException, NoSuchFieldException, IllegalAccessException {
        return ResultSetToTodo.todosModel(todoRepository.getTodos());
    }



   public void generatePdfFromTodo(int todoId){
//        try {
//        Todo todo =  todoRepository.getTodo(todoId);
//            PdfGenerator.getPdf(todo.getSummary() ,todo.toString());
//            System.out.println("Pdf created for todo " + todo.getId() );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void generateSheet() throws SQLException, IllegalAccessException, NoSuchFieldException, IOException {
     ResultSet result =  todoRepository.getTodos();
        ExcelSheetGenerator.generate(result);
    }

}
