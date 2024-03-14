package com.aws.epl.demo.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;


public record ErrorResult(
		HttpStatus status,
		String path,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") Timestamp  timestamp,
		String message) {

}
