package com.aws.epl.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMenuItemsOutput {

	private String pageTage;
	private List<UserMenuItems> items;
}
