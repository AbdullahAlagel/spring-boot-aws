package com.aws.epl.demo.entity;

import java.time.LocalDateTime;

import com.aws.epl.demo.enums.RecordActivityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "numeric(10, 0)",nullable = false)
	private Long id;

	@Column(name = "TYPE", columnDefinition = "int", updatable = false)
	private Integer type;
	
	@Column(name = "USER_ID", columnDefinition = "varchar(255)", nullable = false,unique = true)
	private String userId;
	
	@Column(name = "PASSWORD", columnDefinition = "varchar(255)", nullable = false)
	private String password;
	
	@Column(name = "EMAIL", columnDefinition = "varchar(255)", nullable = false)
	private String email;
	
	@Column(name = "NAME", columnDefinition = "varchar(255)", nullable = false)
	private String username;
	
	@Column(name = "BAD_LOGIN_COUNT", columnDefinition = "smallint")
	private Integer badLoginCount;
	
	@Column(name = "USER_CREATE_DATE", columnDefinition = "datetime")
	private LocalDateTime userCreationDate = LocalDateTime.now();
	
	@Column(name = "LAST_LOGIN_TIME", columnDefinition = "datetime")
	private LocalDateTime lastLoginTime;
	
	@Column(name = "RECORD_ACTIVITY", columnDefinition = "smallint")
	private RecordActivityType recordActivity = RecordActivityType.ACTIVE;
}
