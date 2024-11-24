package com.subilete.demojwt.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subilete.demojwt.Auth.AuthResponse;
import com.subilete.demojwt.Repository.UserRepository;
import com.subilete.demojwt.Service.UserService;
import com.subilete.demojwt.User.RegisterRequest;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
	
	 private final UserService userService;

	    @PostMapping("/register")
	    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
	    	 try {
	             AuthResponse response = userService.register(request);
	             return ResponseEntity.ok(response);
	         } catch (IllegalArgumentException e) {
	        	 AuthResponse errorResponse = new AuthResponse();
	             errorResponse.setErrorMessage(e.getMessage());  // Puedes usar cualquier campo para pasar el mensaje de error
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	         }
	    }
	    

}
