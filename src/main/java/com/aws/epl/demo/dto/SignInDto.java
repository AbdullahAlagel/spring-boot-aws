package com.aws.epl.demo.dto;

import jakarta.validation.constraints.NotNull;


public record SignInDto(
		@NotNull(message = "username.required") String username,
		@NotNull(message = "userrole.required") String userRole,
		@NotNull(message = "email.required") String email
		,@NotNull(message = "password.required") String password) {}
