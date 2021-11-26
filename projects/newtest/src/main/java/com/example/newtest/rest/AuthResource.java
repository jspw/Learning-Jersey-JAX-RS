package com.example.newtest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String login(){
        return "<h1 align='center'>Please sign in to continue</h1>" +
                "<input placeholder='username' >" +
                "<input placeholder='password' >";
    }

}
