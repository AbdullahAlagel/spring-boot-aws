package com.aws.epl.demo.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
	@Column(name = "ID", columnDefinition = "bigint", nullable = false)
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

	@Column(name = "BAD_LOGIN_COUNT")
	private Integer badLoginCount;

	@CreationTimestamp
	@Column(name = "USER_CREATE_DATE", columnDefinition = "date")
	private Timestamp  userCreationDate;

	@Column(name = "LAST_LOGIN_TIME", columnDefinition = "date")
	private Timestamp  lastLoginTime;

	@Column(name = "RECORD_ACTIVITY")
	private RecordActivityType recordActivity = RecordActivityType.ACTIVE;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private List<Role> userRole = new ArrayList<>();
}
