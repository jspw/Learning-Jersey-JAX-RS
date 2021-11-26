package com.example.newtest.repository;

import com.example.newtest.config.DbManager;
import com.example.newtest.config.JdbcConnection;
import com.example.newtest.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
//    private DbManager dbManager = new DbManager();
//    private Connection connection =  dbManager.createConnection();;

//    public TodoRepository() throws SQLException {
//    }

    public void createTodo(Todo todoToAdd) throws SQLException {
        String sql = "insert into todos(summary,description) values(?,?)";
        preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,todoToAdd.getSummary());
        preparedStatement.setString(2, todoToAdd.getDescription());

        preparedStatement.executeUpdate();
    }

    public List<Todo> getAllTodos () throws SQLException {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos";
        preparedStatement = JdbcConnection.getConnection().prepareStatement(sql);
        resultSet =  preparedStatement.executeQuery();
        while (resultSet.next()){
            Todo todo = new Todo();
            todo.setId(resultSet.getInt(1));
            todo.setSummary(resultSet.getString(2));
            todo.setDescription((resultSet.getString(3)));
            todos.add(todo);
        }

        return  todos;
    }
}
