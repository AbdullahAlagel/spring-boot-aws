package com.aws.epl.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.epl.demo.dto.SignInDto;
import com.aws.epl.demo.entity.User;
import com.aws.epl.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/find-users")
	public ResponseEntity<?> findAllUsers() {
		log.info("find All Users");
		List<User> allUser = userService.findAllUser();
		return ResponseEntity.ok().body(allUser);

	}

}
