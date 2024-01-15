package com.aws.epl.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseInput(@NotNull(message = "course.name.required")@NotBlank(message = "course.name.required")  String courseName,
		@NotNull(message = "inserted.date.required") String insertDate) {}

