package com.aws.epl.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.epl.demo.dto.CourseInput;
import com.aws.epl.demo.dto.StudentDto;
import com.aws.epl.demo.entity.Course;
import com.aws.epl.demo.entity.Student;
import com.aws.epl.demo.service.CourseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@PostMapping("/new-course")
	public ResponseEntity<?> addNewCourse(@Valid @RequestBody CourseInput courseInput) {
		String addNewCourse = courseService.addNewCourse(courseInput);
		return ResponseEntity.ok().body(addNewCourse);
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<?> findCourseById(@PathVariable Long id) {
		Course result = courseService.findCourseById(id);
		return ResponseEntity.ok().body(result);

	}

	@GetMapping("/course")
	public ResponseEntity<?> findAllCourse() {
		List<Course> findAllCourse = courseService.findAllCourse();
		return ResponseEntity.ok().body(findAllCourse);

	}
	@PutMapping(value = "course-update/{id}")
	public ResponseEntity<?> updateStudentById(@PathVariable(name = "id") Long id,
			@Valid @RequestBody CourseInput dto) {
		 String updateCourse = courseService.updateStudentById(id,dto);
		return ResponseEntity.ok().body(updateCourse);
	}
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity<?> deleteCourse() {
		return null;

	}

}
