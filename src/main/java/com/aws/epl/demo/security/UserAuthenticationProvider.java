package com.aws.epl.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import com.aws.epl.demo.enums.RecordActivityType;
import com.aws.epl.demo.repo.UserRepository;

import lombok.extern.log4j.Log4j2;


@Transactional
@Log4j2
public class UserAuthenticationProvider  extends DaoAuthenticationProvider {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		try {
			Authentication auth = super.authenticate(authentication);
 			userRepo.resetBadLogin(username, RecordActivityType.ACTIVE);
			log.info("Authentication success for User {}", username);
			return auth;
		} catch (InternalAuthenticationServiceException ex) {
			log.error("Authentication failed for User {} Reason: {}", username, ex.getMessage());
			throw new BadCredentialsException("incorrect.credentials");
		} catch (BadCredentialsException ex) {
			log.error("Authentication failed for User {} Reason: {}", username, ex.getMessage());
//			userRepo.incrementBadLogin(username);
			throw new BadCredentialsException("incorrect.credentials");
		} catch (LockedException ex) {
			log.error("Authentication failed for User {} Reason: {}", username, ex.getMessage());
			throw new LockedException("user.locked");
		}

	}
}
