package com.test_app.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@SuppressWarnings("all")
public class HttpResource {
    @Context
    private Configuration resourceConfig;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewEntry(Entry entry) {
        return Response.ok("New request received: " + entry.data).build();
    }
}
