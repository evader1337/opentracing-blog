package com.dk.tracingblog.gama;

import org.eclipse.microprofile.opentracing.ClientTracingRegistrar;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("gama")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class Resource {
    private Client client = ClientTracingRegistrar.configure(ClientBuilder.newBuilder()).build();

    @Inject
    private Database database;

    @GET
    public Response get() {
        Response r = client
                .target("http://localhost:8084/v1")
                .path("delta")
                .request()
                .get();
        String response = r.readEntity(String.class);
        return Response.ok( database.get(1) + "->" + response).build();
    }
}
