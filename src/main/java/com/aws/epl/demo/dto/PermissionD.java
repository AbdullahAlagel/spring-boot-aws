package com.aws.epl.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionD{
	
	private String name;
	private String tag;
	private Integer orderList;
	private String url;
	private LocalDateTime loginTime;
	private Boolean hasParentPage;
	private ParentPageDto parentPageDto;
}
 