package org.shft.jax.messenger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @Path("/done")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String done() {
    	return "<h1>Are you done ? </h1>";
    }
    
    @Path("/ok")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String ok() {
    	return "<h1>Are you ok ....? </h1>";
    }
}
