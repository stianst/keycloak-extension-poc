package org.keycloak.extension;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.keycloak.Keycloak;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MyRealmResourceProviderTest {
    private static Keycloak keycloak;

    @BeforeClass
    public static void before() {
        keycloak = Keycloak.builder()
                .setVersion("24.0.2")
                .addDependency("org.keycloak.extension", "kc-ext-poc", "1.0-SNAPSHOT")
                .start("start-dev");
    }

    @AfterClass
    public static void after() throws TimeoutException {
        keycloak.stop();
    }

    @Test
    public void test() throws IOException {
        try (InputStream is = new URL("http://localhost:8080/realms/master/say-hello/").openStream()) {
            String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            Assert.assertEquals("Hello!", response);
        }
    }

}
