package com.aws.epl.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="TBL_STUDENT")
public class Student {
	
	@Id
	@Column(columnDefinition = "NUMERIC(19,0)")
	private Long id;
	@Column(columnDefinition = "varchar(250)")
	private String firstName;
	@Column(columnDefinition = "varchar(250)")
	private String lastName;
	@Column(columnDefinition = "varchar(250)")
	private Integer age;
	@Column(columnDefinition = "date")
	private LocalDateTime logTime;
}
