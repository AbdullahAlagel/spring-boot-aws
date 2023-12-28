package com.aws.epl.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.epl.demo.dto.StudentDto;
import com.aws.epl.demo.entity.Student;
import com.aws.epl.demo.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	@GetMapping("number1")
	public String getMethod() {
		return "welcome to AWS 1";

	}

	@GetMapping("number2")
	public String getMethod2() {
		return "welcome to AWS 2";

	}

	
//	localhost:8080/api/v1/student
//	{
//	    "firstName":"Ali",
//	    "lastName":"Sami",
//	    "address":"Hail",
//	    "phone":"0595963299"
//	}
	@PostMapping(value = "student")
	public Student addNewStudent(@Valid @RequestBody StudentDto dto) {
		return studentService.addNewStudent(dto);
	}

//	localhost:8080/api/v1/find/1
	@GetMapping(value = "find/{id}")
	public Student findStudentById(@PathVariable(name = "id") Long id) {
		return studentService.findStudentById(id);
	}
	
	@GetMapping(value = "address/{address}")
	public Student findtByAddress(@PathVariable(name = "address") String address) {
		return studentService.findByAddress(address);
	}
	
	@GetMapping(value = "first-name/{firstname}")
	public Student findByFirstName(@PathVariable(name = "firstname") String firstname) {
		return studentService.findByFirstName(firstname);
	}
//	localhost:8080/api/v1/find
	@GetMapping(value = "find")
	public List<Student> findAllStudent() {
		return studentService.findAllStudent();
	}

	@PutMapping(value = "update/{id}")
	public Student updateStudentById(@PathVariable(name = "id") Long id,
			@Valid @RequestBody StudentDto dto) {
		return studentService.updateStudentById(id,dto);
	}
	
//	localhost:8080/api/v1/delete/1
	@DeleteMapping(value = "delete/{id}")
	public String deleteStudentById(@PathVariable(name = "id") Long id) {
		studentService.deleteStudentById(id);
		return "Student Remove Successfuly";
	}
}