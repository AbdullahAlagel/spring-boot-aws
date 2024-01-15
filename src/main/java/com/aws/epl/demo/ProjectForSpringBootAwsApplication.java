package com.aws.epl.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.aws.epl.demo.entity.Course;
import com.aws.epl.demo.entity.Permission;
import com.aws.epl.demo.entity.Role;
import com.aws.epl.demo.entity.Student;
import com.aws.epl.demo.entity.User;
import com.aws.epl.demo.enums.RecordActivityType;
import com.aws.epl.demo.repo.CourseRepository;
import com.aws.epl.demo.repo.PermissionRepository;
import com.aws.epl.demo.repo.RoleRepository;
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
	private final CourseRepository courseRepository;
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	
	
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
	su.setPhone("0595963296");
	studentRepository.save(su);
	
	
	Student su2 = new Student();
	su2.setFirstName("Abdullah");
	su2.setLastName("Ali");
	su2.setAddress("Riyo");
	su2.setPhone("0566963296");
	studentRepository.save(su2);
	
	Student su3 = new Student();
	su3.setFirstName("alaa");
	su3.setLastName("mohammed");
	su3.setAddress("qassim");
	su3.setPhone("0595223296");
	studentRepository.save(su3);
	
	
	Course course = new Course();
	course.setCourseName("AI");
	course.setInsertDate(LocalDateTime.now());
	course.setStudent(Arrays.asList(su3));
	courseRepository.save(course);
	
	Course course2 = new Course();
	course2.setCourseName("ANGULAR");
	course2.setInsertDate(LocalDateTime.now());
	course2.setStudent(Arrays.asList(su3));
	courseRepository.save(course2);
	
	Course course3 = new Course();
	course3.setCourseName("React Native");
	course3.setInsertDate(LocalDateTime.now());
//	course3.setStudent(Arrays.asList(su3));
	courseRepository.save(course3);
	
	//test 
//	Permission main = new Permission();
//	main.setName("HOME2");
//	main.setTag("ADD2");
//	main.setOrderList(12);
//	main.setUrl("main2");
//	main.setLoginTime(LocalDateTime.now());
//	List<Permission> rolePer = new ArrayList<>();
//	rolePer.add(main);
	// ADD ROLES 
//	Role superAdmin = new Role();
//	superAdmin.setName("SUPER_ADMIN_ROLE");
//	superAdmin.setDescription("THIS IS A SUPER ADMIN ROLE");
//	superAdmin.setLevel("1");
//	roleRepository.save(superAdmin);
	
	
//	Role admin = new Role();
//	admin.setName("ADMIN_ROLE");
//	admin.setDescription("THIS IS AN ADMIN ROLE");
//	admin.setLevel("2");
//	admin.setRolePermissions(rolePer);
//	roleRepository.save(admin);
	
//	Role teacher = new Role();
//	teacher.setName("TEACHER_ROLE");
//	teacher.setDescription("THIS IS A Teacher ROLE");
//	teacher.setLevel("3");
//	roleRepository.save(teacher);
	
//	Role student = new Role();
//	student.setName("STUDENT_ROLE");
//	student.setDescription("THIS IS A STUDENT ROLE");
//	student.setLevel("4");
//	roleRepository.save(student);
	
	
	// ADD PERMISSIONS 
	
//	Permission permiss = new Permission();
//	permiss.setName("HOME");
//	permiss.setTag("ADD");
//	permiss.setOrderList(1);
//	permiss.setUrl("main");
//	permiss.setLoginTime(LocalDateTime.now());
////	main.setPermissionRole(rolePer);
//	permissionRepository.save(permiss);
	
//	Permission addUsers = new Permission();
//	addUsers.setName("ADD_USER");
//	addUsers.setTag("ADD");
//	addUsers.setOrderList(2);
//	addUsers.setUrl("");
//	addUsers.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(addUsers);
	
//	Permission findUser = new Permission();
//	findUser.setName("LIST_USER");
//	findUser.setTag("FIND");
//	findUser.setOrderList(3);
//	findUser.setUrl("");
//	findUser.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(findUser);
	
//	Permission addCourse = new Permission();
//	addCourse.setName("ADD_EDIT_COURSE");
//	addCourse.setTag("ADD");
//	addCourse.setOrderList(4);
//	addCourse.setUrl("/course");
//	addCourse.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(addCourse);
	
//	Permission findCourse = new Permission();
//	findCourse.setName("FIND_ALL_COURSES");
//	findCourse.setTag("FIND");
//	findCourse.setOrderList(5);
//	findCourse.setUrl("/find-courses");
//	findCourse.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(findCourse);
	
//	Permission addEditStudent = new Permission();
//	addEditStudent.setName("ADD_EDIT_STUDENT");
//	addEditStudent.setTag("ADD");
//	addEditStudent.setOrderList(6);
//	addEditStudent.setUrl("/student/add-edit-student");
//	addEditStudent.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(addEditStudent);
	
//	Permission findStudent = new Permission();
//	findStudent.setName("FIND_ALL_STUDENT");
//	findStudent.setTag("FIND");
//	findStudent.setOrderList(7);
//	findStudent.setUrl("/student/find-students");
//	findStudent.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(findStudent);
	
//	Permission notification = new Permission();
//	notification.setName("NOTIFICATION");
//	notification.setTag("NOTIFICATION");
//	notification.setOrderList(7);
//	notification.setUrl("");
//	notification.setLoginTime(LocalDateTime.now());
//	permissionRepository.save(notification);

	
	
	
	}

}
