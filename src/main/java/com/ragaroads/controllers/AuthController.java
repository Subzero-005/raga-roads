package com.ragaroads.controllers;

import com.ragaroads.dtos.LoginRequest;
import com.ragaroads.dtos.LoginResponse;
import com.ragaroads.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Map<String, Object[]> USERS = Map.of(
        "admin",    new Object[]{1L, "admin123",   "Administrator"},
        "manager",  new Object[]{2L, "manager123", "Band Manager"},
        "artist1",  new Object[]{3L, "artist123",  "Artist"},
        "artist2",  new Object[]{4L, "artist123",  "Artist"},
        "artist3",  new Object[]{5L, "artist123",  "Artist"}
    );

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();

        Object[] user = USERS.get(request.getUsername());
        if (user == null) {
            response.setAuthenticated(false);
            response.setMessage("Invalid username");
            return ResponseEntity.status(401).body(response);
        }

        if (!user[1].equals(request.getPassword())) {
            response.setAuthenticated(false);
            response.setMessage("Invalid password");
            return ResponseEntity.status(401).body(response);
        }

        String token = jwtUtil.generateToken(request.getUsername(), (String) user[2]);

        response.setAuthenticated(true);
        response.setUserId((Long) user[0]);
        response.setUsername(request.getUsername());
        response.setRole((String) user[2]);
        response.setToken(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(
            @RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) token = token.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "username", jwtUtil.extractUsername(token),
                    "role", jwtUtil.extractRole(token)
                ));
            }
        } catch (Exception e) {
            // fall through
        }
        return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Invalid token"));
    }
}
