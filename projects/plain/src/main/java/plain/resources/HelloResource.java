package plain.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String index() {
	return "<h1>Hello motherfuckersS</h1>";
    }
}
