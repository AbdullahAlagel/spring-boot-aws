package com.aws.epl.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.epl.demo.dto.LoginInfoOutput;
import com.aws.epl.demo.dto.LoginRequest;
import com.aws.epl.demo.security.UserAuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserAuthController {

	private final AuthenticationManager authenticationManager;
	private final UserAuthService service;

	@PostMapping("/sessions")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		log.info("Login - username:{}", loginRequest.username());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		if (authentication.isAuthenticated()) {
			LoginInfoOutput loginService = service.login(loginRequest);
			return ResponseEntity.ok().body(loginService);
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

}
