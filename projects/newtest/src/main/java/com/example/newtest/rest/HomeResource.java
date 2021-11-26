package com.example.newtest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HomeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(){
        String name = "Mehedi Hasan Shifat";
        return Response.status(200).entity(name).build();
    }
}
