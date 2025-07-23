package com.tvm.CONTROLLER;

import com.tvm.DTO.LoginRequest;
import com.tvm.DTO.TokenResponse;
import com.tvm.MODEL.User;
import com.tvm.REPOSITROY.Userrepo;
import com.tvm.SECURITY_CONFIG.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class MainController {

    @Autowired
    private Userrepo repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request,
                           @RequestParam String role) {
        if (repo.findByUsername(request.getUsername()) != null) {
            return "Username already exists!";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(role.toUpperCase()); // USER or VENDOR
        repo.save(user);

        return "Registered Successfully with role: " + role.toUpperCase();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
            User user = repo.findByUsername(request.getUsername());
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader(value = "Authorization", required = false) String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing or invalid Authorization header");
        }

        String token = header.substring(7); // Remove Bearer prefix

        try {
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            String username = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);
            return ResponseEntity.ok("Token is valid for user: " + username + " with role: " + role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token: " + e.getMessage());
        }
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}
