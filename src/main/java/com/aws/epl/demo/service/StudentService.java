package com.aws.epl.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aws.epl.demo.dto.StudentDto;
import com.aws.epl.demo.entity.Student;
import com.aws.epl.demo.exception.StudentNotFoundException;
import com.aws.epl.demo.repo.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

	private final StudentRepository repository;

	public Student addNewStudent(StudentDto studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setAddress(studentDto.getAddress());
		student.setPhone(studentDto.getPhone());
		return repository.save(student);
	}

	public Student findStudentById(Long id) {
		Optional<Student> findById = repository.findById(id);
		if (findById.isEmpty()) {
			throw new StudentNotFoundException("Student Not Found");
		}
		return findById.get();
	}
	
	public Student findByAddress(String address) {
		Optional<Student> findByAddress = repository.findByAddress(address);
		if (findByAddress==null) {
			throw new StudentNotFoundException("Address for Student Not Found");
		}
		return findByAddress.get();
	}
	
	public Student findByFirstName(String firstName) {
		Optional<Student> findByFirstName = repository.findByFirstName(firstName);
		if (findByFirstName.isEmpty()) {
			throw new StudentNotFoundException("First Name for Student Not Found");
		}
		return findByFirstName.get();
	}

	public List<Student> findAllStudent() {
		return repository.findAll();
	}
	
	public void deleteStudentById(Long id) {
		repository.deleteById(id);
	}

	public Student updateStudentById(Long id, StudentDto dto) {
		Optional<Student> studentFromDB = repository.findById(id);
		if (studentFromDB.isEmpty()) {
			throw new StudentNotFoundException("Student Not Found");
		}
		studentFromDB.get().setFirstName(dto.getFirstName());
		studentFromDB.get().setLastName(dto.getLastName());
		studentFromDB.get().setAddress(dto.getAddress());
		studentFromDB.get().setPhone(dto.getPhone());
		repository.save(studentFromDB.get());
		return studentFromDB.get();
	}

}
