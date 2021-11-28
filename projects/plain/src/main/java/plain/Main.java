package plain;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    public static String base_url = "http://localhost:8080/plain";

    public static final HttpServer startServer() {
	final ResourceConfig rc = new ResourceConfig().packages("com.exp.plain");
	return GrizzlyHttpServerFactory.createHttpServer(URI.create(base_url), rc);
    }

    public static void main(String[] args) throws IOException {
	final HttpServer server = startServer();
	server.start();
	System.out.println(String.format(
		"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
		base_url));
	System.in.read();
	server.stop();
    }

}
