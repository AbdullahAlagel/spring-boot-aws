package com.aws.epl.demo.dto;

import java.sql.Timestamp;

import com.aws.epl.demo.enums.RecordActivityType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserLoginInfo {

	private Long id;
	private Integer type;
	private String userId;
	private String name;
	private Timestamp  userCreationDate;
	private Integer badLoginCount;
	private Timestamp  lastLoginTime;
	private RecordActivityType recordActivity;
//	private String roleName;
	private String password;
	
}
