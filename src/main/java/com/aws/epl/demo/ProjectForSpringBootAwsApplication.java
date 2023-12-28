package com.aws.epl.demo;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.aws.epl.demo.entity.Student;
import com.aws.epl.demo.entity.User;
import com.aws.epl.demo.enums.RecordActivityType;
import com.aws.epl.demo.repo.StudentRepository;
import com.aws.epl.demo.repo.UserRepository;
import com.aws.epl.demo.utils.PasswordService;

import lombok.AllArgsConstructor;

@EnableFeignClients
@SpringBootApplication
@AllArgsConstructor
public class ProjectForSpringBootAwsApplication implements CommandLineRunner{
	
	private final PasswordService passwordService;
	private final  UserRepository userRepository;
	private final  StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectForSpringBootAwsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	User user = new User();
	user.setType(1);
	user.setRecordActivity(RecordActivityType.ACTIVE);
	user.setUserId("admin");
	user.setUsername("admin");
	user.setBadLoginCount(0);
	user.setPassword(passwordService.encrypt("Aa123456"));
	user.setEmail("aa@gmail.com");
	user.setLastLoginTime(LocalDateTime.now() );
	userRepository.save(user);
	
	
	User user2 = new User();
	user2.setType(2);
	user2.setRecordActivity(RecordActivityType.ACTIVE);
	user2.setUserId("ali");
	user2.setUsername("ali");
	user2.setBadLoginCount(0);
	user2.setPassword(passwordService.encrypt("Aa123456"));
	user2.setEmail("ali@gmail.com");
	user2.setLastLoginTime(LocalDateTime.now());
	userRepository.save(user2);
	
	
	Student su = new Student();
	su.setFirstName("sami");
	su.setLastName("saad");
	su.setAddress("jeddah");
	studentRepository.save(su);
	Student su2 = new Student();
	
	su2.setFirstName("Abdullah");
	su2.setLastName("Ali");
	su2.setAddress("Riyo");
	studentRepository.save(su2);
	
	Student su3 = new Student();
	su3.setFirstName("alaa");
	su3.setLastName("mohammed");
	su3.setAddress("qassim");
	studentRepository.save(su3);
	
	}

}
