package com.example.newtest.rest;


import com.example.newtest.model.Todo;
import com.example.newtest.repository.TodoRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/todo")
public class TodoResource {

    private TodoRepository todoRepository;

    public TodoResource(){
        this.todoRepository = new TodoRepository();
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
    public Todo getTodo(@PathParam("id") String id){
        System.out.println("Todo id : " + id);
        Todo todo = new Todo();
        todo.setSummary(id + " : Application JSON todo Summary");
        todo.setDescription("There is a b description about this todo, Please dont check this!");
        return todo;
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createTodo(Todo todo)  {
        System.out.println(todo);
        try {
            todoRepository.createTodo(todo);
            return Response.status(Response.Status.CREATED).entity (todo.toString()).build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }




}
