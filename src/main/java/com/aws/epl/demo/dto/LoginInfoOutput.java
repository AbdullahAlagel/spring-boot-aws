package com.aws.epl.demo.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Builder;

@Builder
public record LoginInfoOutput(Long id,String username, String name, String token,
		Timestamp  lastLogin, Timestamp expirtionTime,List<UserMenuItemsOutput> menuItem ) {
}
