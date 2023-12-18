package com.aws.epl.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.epl.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
