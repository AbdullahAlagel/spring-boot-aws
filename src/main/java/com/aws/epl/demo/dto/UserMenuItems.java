package com.aws.epl.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuItems {
	
	private Integer pageOrder;
	private String pageTag;
	private String permissionName;
	private String permissionTag;
	private String url;
//	private String permissionName;
	
	
	public static UserMenuItems buildUserMenuItems(Object[] item) {
		return new UserMenuItems((Integer) item[0], (String) item[1], (String) item[2], (String) item[3],
				(String) item[4]);
	}
}
