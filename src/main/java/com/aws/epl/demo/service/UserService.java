package com.aws.epl.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aws.epl.demo.entity.User;
import com.aws.epl.demo.repo.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<User> findAllUser() {
		List<User> findAll = userRepository.findAll();
		return findAll;
	}

}
