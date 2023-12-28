package com.aws.epl.demo.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record LoginInfoOutput(Long id,String username, String name, String token,
		LocalDateTime lastLogin, LocalDateTime expirtionTime) {
}
