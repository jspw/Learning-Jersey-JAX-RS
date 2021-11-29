package com.example.newtest.service;

import com.example.newtest.model.Todo;
import com.example.newtest.repository.TodoRepository;
import com.example.newtest.utility.PdfGenerator;

import java.sql.SQLException;

public class TodoService {
    private TodoRepository todoRepository = new TodoRepository();

   public void generatePdfFromTodo(int todoId){
        try {
        Todo todo =  todoRepository.getTodo(todoId);
            PdfGenerator.getPdf(todo.getSummary() ,todo.toString());
            System.out.println("Pdf created for todo " + todo.getId() );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
