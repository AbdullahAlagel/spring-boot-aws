package com.aws.epl.demo.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_PERMISSION")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "NAME", columnDefinition = "varchar(250)",unique = true)
	private String name;

	@Column(name = "TAG", columnDefinition = "varchar(250)",unique = true)
	private String tag;

	@Column(name = "ORDER_LIST", columnDefinition = "int")
	private Integer orderList;

	@Column(name = "URL", columnDefinition = "varchar(250)")
	private String url;

	@CreationTimestamp
	@Column(name = "LOGIN_TIME")
	private Timestamp  loginTime;

	@ManyToMany(mappedBy = "rolePermissions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Role> permissionRole = new ArrayList<>();
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_PAGES_ID")
	private ParentPage parentPage;
}
