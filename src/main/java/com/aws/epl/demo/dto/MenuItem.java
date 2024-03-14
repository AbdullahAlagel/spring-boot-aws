package com.aws.epl.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {

	private String label;
	private String routerLink;
	private List<MenuItem> items;
}
