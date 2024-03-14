package com.aws.epl.demo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aws.epl.demo.dto.CourseInput;
import com.aws.epl.demo.entity.Course;
import com.aws.epl.demo.repo.CourseRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;

	public String addNewCourse(CourseInput courseInput) {
		log.info("Course Name : {} and inserted Date : {} ",courseInput.courseName(),courseInput.insertDate());
		Course course = new Course();
		course.setCourseName(courseInput.courseName());
		course.setInsertDate(new Timestamp(System.currentTimeMillis()));
		courseRepository.save(course);

		return "Course Added Successfuly";
	}

	public Course findCourseById(Long courseId) {
		Optional<Course> result = courseRepository.findById(courseId);
		return result.get();
	}

	public void deleteCourse() {

	}

	public List<Course> findAllCourse() {
		return courseRepository.findAll();
	}

	public String updateStudentById(Long id, @Valid CourseInput dto) {
		Optional<Course> result = courseRepository.findById(id);
		result.get().setCourseName(dto.courseName());
		result.get().setInsertDate(new Timestamp(System.currentTimeMillis()));
		courseRepository.save(result.get());
		return "Course Updated Successfuly";
	}
}
