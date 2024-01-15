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
import com.aws.epl.demo.dto.ParentPageDto;
import com.aws.epl.demo.dto.PermissionD;
import com.aws.epl.demo.service.PermissionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PermissionController {

	private final PermissionService permissionService;

	@PostMapping("/add-permission")
	public ResponseEntity<String> addNewPermission(@Valid @RequestBody PermissionD permission) {
		log.info("Permisssion Input: {} ", permission.getName());
		String addNewPermission = permissionService.addNewPermission(permission);
		return ResponseEntity.ok(addNewPermission);
	}

	@PostMapping("/add-permissions")
	public ResponseEntity<String> addNewPermissions(@Valid @RequestBody List<PermissionD> permission) {
		log.info("Permisssions Input: {} ");
		String addNewPermission = permissionService.addNewPermissions(permission);
		return ResponseEntity.ok(addNewPermission);
	}

	@GetMapping("/permissions")
	public ResponseEntity<?> findAllPermissions() {
		return ResponseEntity.ok().body(permissionService.findAllPermission());

	}

	@PutMapping(value = "permission-update/{id}")
	public ResponseEntity<?> updatePermission(@PathVariable(name = "id") Long id, @Valid @RequestBody CourseInput dto) {
		String updatePermission = permissionService.updatePermission(id, dto);
		return ResponseEntity.ok().body(updatePermission);
	}

	@DeleteMapping("/permission/{id}")
	public ResponseEntity<?> deletePermission(@PathVariable(name = "id") Long id) {
		String deletePermission = permissionService.deletePermission(id);
		return ResponseEntity.ok().body(deletePermission);
	}

	@PostMapping("/parent-page")
	public ResponseEntity<String> addNewParentPage(@Valid @RequestBody ParentPageDto parentPageDto) {
		String addNewPagePermission = permissionService.addNewParentPage(parentPageDto);
		return ResponseEntity.ok(addNewPagePermission);
	}
	@PostMapping("/parent-pages")
	public ResponseEntity<String> addNewParentPages(@Valid @RequestBody List<ParentPageDto> parentPageDto) {
		String addNewPagePermission = permissionService.addNewParentPages(parentPageDto);
		return ResponseEntity.ok(addNewPagePermission);
	}

	@GetMapping("/parent-page")
	public ResponseEntity<?> findAllPages() {
		return ResponseEntity.ok().body(permissionService.findAllPages());

	}
}
