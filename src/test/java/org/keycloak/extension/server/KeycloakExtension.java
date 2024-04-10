package org.keycloak.extension.server;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.keycloak.Keycloak;
import org.keycloak.common.Version;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class KeycloakExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback {

    private final static boolean GLOBAL = true;

    private static Keycloak KEYCLOAK;

    private List<Injector> injectors = List.of(
            new KeycloakAdminClientInjector(),
            new KeycloakServerUrlInjector()
    );

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (KEYCLOAK == null) {
            KEYCLOAK = Keycloak.builder()
                    .setVersion(Version.VERSION)
                    .addDependency("org.keycloak.extension", "kc-ext-poc", "1.0-SNAPSHOT")
                    .start("start-dev", "--db=dev-mem", "--cache=local");

            if (GLOBAL) {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        KEYCLOAK.stop();
                    } catch (TimeoutException e) {
                        throw new RuntimeException(e);
                    }
                }));
            }
        } else if (!GLOBAL) {
            throw new Exception("Keycloak already running");
        }
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        if (!GLOBAL) {
            KEYCLOAK.stop();
            KEYCLOAK = null;
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Object testInstance = context.getRequiredTestInstance();
        inject(testInstance);
    }

    String getServerUrl() {
        return "http://localhost:8080";
    }

    private void inject(Object o) throws IllegalAccessException {
        for (Field f : o.getClass().getDeclaredFields()) {
            for (Injector i : injectors) {
                KeycloakServerUrl annotation = f.getAnnotation(i.getAnnotation());
                if (annotation != null) {
                    f.setAccessible(true);
                    f.set(o, i.getSupplier().get());
                }
            }
        }
    }

    private interface Injector {

        public <T extends Annotation> Class<T> getAnnotation();

        public Supplier<Object> getSupplier();

    }

    private class KeycloakServerUrlInjector implements Injector {

        @Override
        public <T extends Annotation> Class<T> getAnnotation() {
            return (Class<T>) KeycloakServerUrl.class;
        }

        @Override
        public Supplier<Object> getSupplier() {
            return KeycloakExtension.this::getServerUrl;
        }
    }

    private class KeycloakAdminClientInjector implements Injector {

        @Override
        public Class getAnnotation() {
            return KeycloakAdminClient.class;
        }

        @Override
        public Supplier<Object> getSupplier() {
            return () -> org.keycloak.admin.client.Keycloak.getInstance(getServerUrl(), "master", "admin", "admin", "admin-cli");
        }
    }

}
