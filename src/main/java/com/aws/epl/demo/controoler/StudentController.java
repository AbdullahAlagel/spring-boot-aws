package com.aws.epl.demo.controoler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {

	@GetMapping("number1")
	public String getMethod() {
		return "welcome to AWS 1";
		
	}
	@GetMapping("number2")
	public String getMethod2() {
		return "welcome to AWS 2";
		
	}
	
}
