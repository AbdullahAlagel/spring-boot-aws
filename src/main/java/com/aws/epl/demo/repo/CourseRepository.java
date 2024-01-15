package com.aws.epl.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.epl.demo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
