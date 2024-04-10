package org.keycloak.extension;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.extension.server.KeycloakExtension;
import org.keycloak.extension.server.KeycloakServerUrl;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.quarkus.runtime.integration.QuarkusKeycloakSessionFactory;
import org.keycloak.representations.info.ProviderRepresentation;
import org.keycloak.representations.info.ServerInfoRepresentation;
import org.keycloak.services.resource.RealmResourceProvider;

import java.io.IOException;
import java.util.Map;

@ExtendWith(KeycloakExtension.class)
public class MyRealmResourceProvider2Test {

    @KeycloakServerUrl
    String serverUrl;


    @Test
    public void test() throws IOException {
        Assertions.assertNotNull(serverUrl);
    }
    @Test
    public void test2() throws IOException {
    }

}
