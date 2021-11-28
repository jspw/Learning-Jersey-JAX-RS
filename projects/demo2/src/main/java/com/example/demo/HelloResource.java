package com.example.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class HelloResource {
    @GET
    @Produces("text/html")
    public String hello() {
        return "<h1>Hello, World!</h1>";
    }
}