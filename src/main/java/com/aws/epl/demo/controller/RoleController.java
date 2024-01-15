package com.aws.epl.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.epl.demo.dto.CourseInput;
import com.aws.epl.demo.dto.RoleDto;
import com.aws.epl.demo.service.RoleService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RoleController {

	private final RoleService roleService;
	
	
	@PostMapping("/new-role")
	public ResponseEntity<Void> addNewRole(@Valid @RequestBody RoleDto roleDto) {
		roleService.addNewRole(roleDto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/new-roles")
	public ResponseEntity<?> addNewRoles(@Valid @RequestBody List<RoleDto> roleDto) {
		String addNewRoles = roleService.addNewRoles(roleDto);
		return ResponseEntity.ok(addNewRoles);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<?> findAllRoles() {
		return ResponseEntity.ok().body(roleService.findAllRoles());

	}
	
	@GetMapping("/role/{roleName}")
	public ResponseEntity<?> findByRole(@PathVariable String roleName) {
		return ResponseEntity.ok().body(roleService.findByRole(roleName));

	}
	
	@PutMapping(value = "role-update/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id,
			@Valid @RequestBody CourseInput dto) {
		 String updateCourse = roleService.updateRole(id,dto);
		return ResponseEntity.ok().body(updateCourse);
	}
	
	@DeleteMapping("/role/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(name = "id") Long id) {
		 String deleteRole = roleService.updateRole(id);
		 return ResponseEntity.ok().body(deleteRole);
	}
}
