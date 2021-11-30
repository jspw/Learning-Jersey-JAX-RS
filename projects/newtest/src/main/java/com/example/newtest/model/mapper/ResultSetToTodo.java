package com.example.newtest.model.mapper;

import com.example.newtest.model.Todo;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToTodo {

    public static Todo todoModel(ResultSet result) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Todo todo = new Todo();
        Field field ;
        int columnCount =  result.getMetaData().getColumnCount();
        while(result.next()){
            for (int i = 1; i <= columnCount; i++) {
                String fieldName = result.getMetaData().getColumnName(i);
                    field =   Todo.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(todo,result.getObject(fieldName));
            }
        }
        return todo;
    }

    public static List<Todo> todosModel(ResultSet result) throws SQLException, IllegalAccessException,
            NoSuchFieldException {
        List<Todo>todos = new ArrayList<>();
        while(result.next()){
           Todo todo = todoModel(result);
            todos.add(todo);
        }
        return todos;
    }
}
