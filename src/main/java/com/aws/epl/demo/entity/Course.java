package com.aws.epl.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aws.epl.demo.enums.RecordActivityType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_COURSE")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	,unique = true
	@Column(name = "COURSE_NAME", columnDefinition = "varchar(255)")
	private String courseName;

//	@Column(name = "LAST_NAME",columnDefinition = "varchar(50)")
//	private String lastName;

	@Column(name = "INSERT_DATE", columnDefinition = "date")
	private LocalDateTime insertDate;

	@Column(name = "RECORD_ACTIVITY", columnDefinition = "smallint")
	private RecordActivityType recordActivity = RecordActivityType.ACTIVE;

	@ManyToMany()
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnore
	private List<Student> student = new ArrayList<>();
}
