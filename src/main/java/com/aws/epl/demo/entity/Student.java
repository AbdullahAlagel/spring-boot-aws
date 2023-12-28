package com.aws.epl.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="TBL_STUDENT")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	,unique = true
	@Column(name = "FIRST_NAME",columnDefinition = "varchar(50)")
	private String firstName;
	
	@Column(name = "LAST_NAME",columnDefinition = "varchar(50)")
	private String lastName;
	
	@Column(name = "ADDRESS",columnDefinition = "varchar(50)")
	private String address;
	
	@Column(name = "PHONE",columnDefinition = "varchar(15)")
	private String phone; 
}
