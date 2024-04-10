package org.keycloak.extension;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.keycloak.services.resource.RealmResourceProvider;

public class MyRealmResourceProvider implements RealmResourceProvider {

    @Override
    public Object getResource() {
        return this;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public String sayHello() {
        return "Hello!";
    }

    @Override
    public void close() {
    }
}
