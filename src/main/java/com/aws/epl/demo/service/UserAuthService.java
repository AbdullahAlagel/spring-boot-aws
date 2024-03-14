package com.aws.epl.demo.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aws.epl.demo.dto.LoginInfoOutput;
import com.aws.epl.demo.dto.LoginRequest;
import com.aws.epl.demo.dto.MenuItem;
import com.aws.epl.demo.dto.SignInDto;
import com.aws.epl.demo.dto.UserLoginInfo;
import com.aws.epl.demo.dto.UserMenuItems;
import com.aws.epl.demo.dto.UserMenuItemsOutput;
import com.aws.epl.demo.entity.Role;
import com.aws.epl.demo.entity.User;
import com.aws.epl.demo.enums.RecordActivityType;
import com.aws.epl.demo.enums.Roles;
import com.aws.epl.demo.exception.GeneralException;
import com.aws.epl.demo.repo.RoleRepository;
import com.aws.epl.demo.repo.UserRepository;
import com.aws.epl.demo.security.JWTBuildService;
import com.aws.epl.demo.utils.PasswordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserAuthService {

	private final UserRepository userRepository;
	private final JWTBuildService jwtService;
	private final PasswordService passwordService;
	private final RoleRepository roleRepository;

	 private @Value("${token.expirationtime:130}") Short tokenExpirationtime;
	 
	public LoginInfoOutput login(LoginRequest loginRequest) {
		UserLoginInfo user = getUserLoginInfo(loginRequest.username());
		LoginInfoOutput userLoginInfo = userLoginInfo(user.getUserId());
		
		
		return userLoginInfo;
	}

	public UserLoginInfo getUserLoginInfo(String username) {
		log.info("Get User Info: {}", username);
		Optional<UserLoginInfo> user = userRepository.findLoginUserInfo(username);
		if (user.isEmpty())
			throw new UsernameNotFoundException("user not found :" + username);
		if (user.get().getRecordActivity() == RecordActivityType.NOT_ACTIVE)
			throw new GeneralException("user.not.active");
		return user.get();
	}

	LoginInfoOutput userLoginInfo(String userId) {
		UserLoginInfo user = getUserLoginInfo(userId);
		// get permission to build MenuItem by role name

		String generateToken = jwtService.generateToken(user, tokenExpirationtime);
		List<Object[]> userRolesAndPer = userRepository.findUserRoleAndPermission(user.getUserId());
		
		Map<String, List<UserMenuItems>> collect = userRolesAndPer.stream().map(UserMenuItems::buildUserMenuItems)
        .collect(Collectors.groupingBy(UserMenuItems::getPageTag, Collectors.toList()));
		
//		List<UserMenuItemsOutput> mapValues;
		List<UserMenuItemsOutput> collect2 = collect.keySet().stream()
        .map(k -> new UserMenuItemsOutput(k, collect.get(k))).collect(Collectors.toList());
		LoginInfoOutput loginResponse;
		// set last login if already logged in before
		Timestamp ts=new Timestamp(System.currentTimeMillis());  
		loginResponse = new LoginInfoOutput(user.getId(), user.getUserId(), user.getName(), generateToken,
				user.getLastLoginTime(),ts,collect2);

		return loginResponse;
	}
	
	public String addNewUser(SignInDto signInDto) {
		User user = new User();
		Role userRole = null;
		if (signInDto.userRole().equals(Roles.SUPER_ADMIN_ROLE.name())) {
			user.setType(1);
			userRole = roleRepository.findByName(Roles.SUPER_ADMIN_ROLE.name());
		}
		if (signInDto.userRole().equals(Roles.ADMIN_ROLE.name())) {
			user.setType(2);
			userRole = roleRepository.findByName(Roles.ADMIN_ROLE.name());
		}
		if (signInDto.userRole().equals(Roles.TEACHER_ROLE.name())) {
			user.setType(3);
			userRole = roleRepository.findByName(Roles.TEACHER_ROLE.name());
		}
		if (signInDto.userRole().equals(Roles.STUDENT_ROLE.name())) {
			user.setType(4);
			userRole = roleRepository.findByName(Roles.STUDENT_ROLE.name());
		}
		Boolean existsUserId = userRepository.existsByUserId(signInDto.username());
		if (existsUserId) {
			throw new GeneralException("username Already is exists");
		}
		user.setRecordActivity(RecordActivityType.ACTIVE);
		user.setUserId(signInDto.username());
		user.setUsername(signInDto.username());
		user.setBadLoginCount(0);
		user.setPassword(passwordService.encrypt(signInDto.password()));
		user.setEmail(signInDto.email());
		user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		if (userRole==null) {
			throw new GeneralException("user role not found");
		}
		user.setUserRole(Arrays.asList(userRole));
		userRepository.save(user);
		return "User has been added";
	}
}
