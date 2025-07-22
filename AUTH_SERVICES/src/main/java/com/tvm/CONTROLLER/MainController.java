package com.tvm.CONTROLLER;

import com.tvm.DTO.LoginRequest;
import com.tvm.DTO.TokenResponse;
import com.tvm.MODEL.User;
import com.tvm.REPOSITROY.Userrepo;
import com.tvm.SECURITY_CONFIG.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public String register(@RequestBody LoginRequest request) {
        if (repo.findByUsername(request.getUsername()) != null) {
            return "Username already exists!";
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        repo.save(user);
        return "Registered Successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String header) {
        try {
            String token = header.substring(7);
            String username = jwtUtil.extractUsername(token);
            return ResponseEntity.ok("Valid for user: " + username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<String> LOOUT(){

        return ResponseEntity.ok("logout successflly");
    }
}
