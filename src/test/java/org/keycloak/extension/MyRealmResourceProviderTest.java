package org.keycloak.extension;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.extension.server.KeycloakExtension;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.quarkus.runtime.integration.QuarkusKeycloakSessionFactory;
import org.keycloak.representations.info.ProviderRepresentation;
import org.keycloak.representations.info.ServerInfoRepresentation;
import org.keycloak.services.resource.RealmResourceProvider;

import java.io.IOException;
import java.util.Map;

@ExtendWith(KeycloakExtension.class)
public class MyRealmResourceProviderTest {

    @Test
    public void test() {

    }

//    @Test
//    public void providerPresent() throws InterruptedException {
//        Thread.sleep(1000);
//        org.keycloak.admin.client.Keycloak kcadmin = org.keycloak.admin.client.Keycloak.getInstance("http://localhost:8080", "master", "admin", "admin", "admin-cli");
//        ServerInfoRepresentation info = kcadmin.serverInfo().getInfo();
//        Map<String, ProviderRepresentation> providers = info.getProviders().get("realm-restapi-extension").getProviders();
//        System.out.println(providers.keySet());
//    }
//
//    @Test
//    public void test() throws IOException {
//        Response response = RestAssured.when().get("http://localhost:8080/realms/master/say-hello/").andReturn();
//        System.out.println(response.statusCode());
//        System.out.println(response.statusLine());
//        Assertions.assertEquals(200, response.statusCode());
//    }
//
//    @Test
//    public void testSessionFactory() {
//        QuarkusKeycloakSessionFactory keycloakSessionFactory = QuarkusKeycloakSessionFactory.getInstance();
//        ProviderFactory<RealmResourceProvider> providerFactory = keycloakSessionFactory.getProviderFactory(RealmResourceProvider.class, "say-hello");
//        Assertions.assertNotNull(providerFactory);
//    }

}
