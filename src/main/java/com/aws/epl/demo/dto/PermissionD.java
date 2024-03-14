package com.aws.epl.demo.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionD{
	
	private String name;
	private String tag;
	private Integer orderList;
	private String url;
	private Timestamp  loginTime;
	private Boolean hasParentPage;
	private ParentPageDto parentPageDto;
}
 