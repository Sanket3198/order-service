package com.ecommerce.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class JwtAuthController {

	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/token")
    public Map<String, String> generateToken(@RequestParam String username) {

        String token = jwtUtil.generateToken(username);
        return Map.of("token", token);
    }
}
