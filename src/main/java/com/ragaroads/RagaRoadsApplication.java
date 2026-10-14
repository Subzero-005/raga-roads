package com.ragaroads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

// JWT auth is handled by AuthController, so Spring's default in-memory user is not needed
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class RagaRoadsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RagaRoadsApplication.class, args);
    }
}
