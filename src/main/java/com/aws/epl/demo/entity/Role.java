package com.aws.epl.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "TBL_ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "numeric(10, 0)",nullable = false)
	private Long id;
	
	@Column(name = "NAME",columnDefinition = "varchar(250)",unique = true)
	private String name;
	
	@Column(name = "DESCRIPTION",columnDefinition = "varchar(250)")
	private String description;
	
	@Column(name = "LEVEL",columnDefinition = "varchar(250)")
	private String level;
	
	@ManyToMany(mappedBy = "userRole",cascade = CascadeType.ALL)
	private List<User> userRole = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_role_permission", 
	joinColumns = @JoinColumn(name = "role_id"), 
	inverseJoinColumns = @JoinColumn(name = "permission_id"))
//	@JsonIgnore
	private List<Permission> rolePermissions = new ArrayList<>();
}
