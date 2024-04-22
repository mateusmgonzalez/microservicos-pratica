package br.com.mateus.microservices.core.review;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

public abstract class MySqlTestBase {

    @ServiceConnection
    public static JdbcDatabaseContainer databaseContainer = new MySQLContainer("mysql:8.0.32").withStartupTimeoutSeconds(300);


    static {
        databaseContainer.start();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.database.url", databaseContainer::getJdbcUrl);
        registry.add("spring.database.username", databaseContainer::getUsername);
        registry.add("spring.database.password", databaseContainer::getPassword);
    }

}
