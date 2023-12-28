package com.aws.epl.demo.security;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aws.epl.demo.dto.LoginInfoOutput;
import com.aws.epl.demo.dto.LoginRequest;
import com.aws.epl.demo.dto.UserLoginInfo;
import com.aws.epl.demo.enums.RecordActivityType;
import com.aws.epl.demo.exception.GeneralException;
import com.aws.epl.demo.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserAuthService {

	private final UserRepository userRepository;
	private final JWTBuildService jwtService;

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

		LoginInfoOutput loginResponse;
		// set last login if already logged in before

		loginResponse = new LoginInfoOutput(user.getId(), user.getUserId(), user.getName(), generateToken,
				user.getLastLoginTime(), LocalDateTime.now());

		return loginResponse;
	}
}
