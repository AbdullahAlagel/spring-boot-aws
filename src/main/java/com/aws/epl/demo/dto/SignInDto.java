package com.aws.epl.demo.dto;

import jakarta.validation.constraints.NotNull;


public record SignInDto(@NotNull(message = "username.required") String username,
		 String email
		,@NotNull(message = "password.required") String password) {}
