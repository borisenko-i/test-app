package com.test_app;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.logging.Logger;

import com.test_app.database.DbConnection;
import com.test_app.resources.HttpResource;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

@SuppressWarnings("all")
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static DbConnection dbConnection;

    private Main() {}

    public static void main(String[] args) {
        Server server = createServer(args[0], Integer.parseInt(args[1]));
        String appAddress = args[0] + ":" + args[1];
        setDbConnection(args[2], args[3], args[4]);
        try {
            server.start();
            LOGGER.info("Started app on " + appAddress);
        } catch (Exception e) {
            LOGGER.severe(String.format("Error while starting app on %s: %s", appAddress, e.getMessage()));
        }
    }

    private static Server createServer(String host, int port) {
        // Create a server listening to HTTP requests
        Server server = new Server(new InetSocketAddress(host, port));
        Connector connector = new ServerConnector(server);
        server.addConnector(connector);

        // Create a servlet holder off of ProducerApiResource, set resource properties
        ResourceConfig resourceConfig = new ResourceConfig().register(HttpResource.class);
        resourceConfig.setProperties(
                Map.of("jersey.config.server.wadl.disableWadl", "true"));
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));

        // Set up context handler to set ProducerApiResource on "/*"
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        contextHandler.addServlet(servletHolder, "/*");

        // Attach handler, stop server at app shutdown
        server.setHandler(contextHandler);
        server.setStopAtShutdown(true);

        return server;
    }

    private static void setDbConnection(String url, String user, String password) {
        dbConnection = new DbConnection();
        dbConnection.url = url;
        dbConnection.user = user;
        dbConnection.password = password;
    }
}