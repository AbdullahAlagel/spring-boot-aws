package com.aws.epl.demo.dto;

import java.util.List;

import com.aws.epl.demo.entity.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentPageDto {
	
	private String displayTag;
	private Integer displayOrder;
	private List<Permission> permissions;
}
