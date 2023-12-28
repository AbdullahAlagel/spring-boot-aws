package com.aws.epl.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {

	@NotBlank(message = "First Name must be not null")
	private String firstName;
	@NotBlank(message = "Last Name must be not null")
	private String lastName;
	@NotBlank(message = "Address must be not null")
	private String address;
	@NotBlank(message = "Phone must be not null")
	@Size(max = 10,min = 10)
	private String phone;
}
