package com.aws.epl.demo.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull(message = "username.required") String username,@NotNull(message = "password.required") String password) {}
