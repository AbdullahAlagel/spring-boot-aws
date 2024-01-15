package com.aws.epl.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aws.epl.demo.enums.RecordActivityType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@Column(name = "ID", columnDefinition = "numeric(10, 0)", nullable = false)
	private Long id;

	@Column(name = "TYPE", columnDefinition = "int", updatable = false)
	private Integer type;

	@Column(name = "USER_ID", columnDefinition = "varchar(255)", nullable = false, unique = true)
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private List<Role> userRole = new ArrayList<>();
}
