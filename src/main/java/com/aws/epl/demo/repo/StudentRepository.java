package com.aws.epl.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.epl.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByFirstName(String firstName);
	
	Optional<Student> findByFirstNameIgnoreCase(String firstName);
	
	Optional<Student> findByAddress(String address);
	
//	@Query("")  JPQL
//	Optional<Student> findByPhone(String address);
	
//	@Query("",nativeQuery = true)    //Native Query
//	Optional<Student> findByPhone(String address);
}
