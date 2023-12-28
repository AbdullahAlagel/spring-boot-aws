package com.aws.epl.demo.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.aws.epl.demo.dto.UserLoginInfo;
import com.aws.epl.demo.repo.UserRepository;

import lombok.AllArgsConstructor;

@Component
//@RequiredArgsConstructor
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepo;
//	private final PermissionRepository permissionRepo;

//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserLoginInfo> userInfo = userRepo.findLoginUserInfo(username);
		// convert userInfo to UserDetails or throw error

//		List<String> permission = permissionRepo.findUserPermission(username);

		UserDetails user = userInfo.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(username));
//		List<SimpleGrantedAuthority> roles = permission.stream().map(r -> new SimpleGrantedAuthority(r)).toList();
//		user.getAuthorities().addAll((Collection) roles);
		return user;
	}
}
