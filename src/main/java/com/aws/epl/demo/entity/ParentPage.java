package com.aws.epl.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_PARENT_PAGE")
public class ParentPage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "numeric(19,0)")
	private Long id;
	
	@Column(name = "DISPLAY_TAG",columnDefinition = "varchar(250)",unique = true)
	private String displayTag;
	
	@Column(columnDefinition = "INT")
	private Integer displayOrder;
	
	@JsonIgnore
	
	@OneToMany(mappedBy = "parentPage", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Permission> permissions;

}
