package com.aws.epl.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


public record ErrorResult(
		HttpStatus status,
		String path,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp,
		String message) {

}
