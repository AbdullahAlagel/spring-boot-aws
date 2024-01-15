package com.aws.epl.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aws.epl.demo.dto.CourseInput;
import com.aws.epl.demo.dto.RoleDto;
import com.aws.epl.demo.entity.Role;
import com.aws.epl.demo.enums.Permission;
import com.aws.epl.demo.enums.Roles;
import com.aws.epl.demo.exception.GeneralException;
import com.aws.epl.demo.repo.PermissionRepository;
import com.aws.epl.demo.repo.RoleRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {

	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;

	public String addNewRole(RoleDto roleDto) {
		List<com.aws.epl.demo.entity.Permission> findPermission = findPermission(roleDto.name());

		if (findPermission == null || findPermission.size() == 0) {
			throw new GeneralException("wrong Role Type");
		}
		Role role = new Role();
		role.setName(roleDto.name());
		role.setDescription(roleDto.description());
		role.setRolePermissions(findPermission);
		role.setLevel(roleDto.level());
		roleRepository.save(role);
		return "Role Added";
	}

	private List<com.aws.epl.demo.entity.Permission> findPermission(String roleType) {
		List<Permission> permission = Arrays.asList(Permission.values());

		if (roleType.equals(Roles.SUPER_ADMIN_ROLE.name())) {
			List<String> list = permission.stream().filter(m -> m.equals(Permission.HOME)).map(m -> m.toString())
					.toList();
			List<com.aws.epl.demo.entity.Permission> findByNameIn = permissionRepository.findByNameIn(list);
			return findByNameIn;
		} else if (roleType.equals(Roles.ADMIN_ROLE.name())) {
			List<String> list = permission.stream().map(m -> m.toString()).toList();
			List<com.aws.epl.demo.entity.Permission> findByNameIn = permissionRepository.findByNameIn(list);
			return findByNameIn;
		} else if (roleType.equals(Roles.TEACHER_ROLE.name())) {
			List<String> list = permission
					.stream().filter(m -> m.equals(Permission.HOME) || m.equals(Permission.ADD_EDIT_COURSE)
							|| m.equals(Permission.FIND_COURSES) || m.equals(Permission.NOTIFICATION))
					.map(m -> m.toString()).toList();
			List<com.aws.epl.demo.entity.Permission> findByNameIn = permissionRepository.findByNameIn(list);
			return findByNameIn;
		} else if (roleType.equals(Roles.STUDENT_ROLE.name())) {
			List<String> list = permission 
					.stream().filter(m -> m.equals(Permission.HOME) || m.equals(Permission.ADD_EDIT_COURSE)
							|| m.equals(Permission.FIND_COURSES) || m.equals(Permission.NOTIFICATION))
					.map(m -> m.toString()).toList();
			List<com.aws.epl.demo.entity.Permission> findByNameIn = permissionRepository.findByNameIn(list);
			return findByNameIn;
		}
		return null;

	}

	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	public String updateRole(Long id) {
		return null;
	}

	public String updateRole(Long id, @Valid CourseInput dto) {
		return null;
	}

	public Role findByRole(String roleName) {
		return roleRepository.findByName(roleName);
	}

	public String addNewRoles(@Valid List<RoleDto> roleDto) {

		List<Role> list = roleDto.stream().map(m -> {
			List<com.aws.epl.demo.entity.Permission> findPermission = findPermission(m.name());

			if (findPermission == null || findPermission.size() == 0) {
				throw new GeneralException("wrong Role Type");
			}

			Role role = new Role();
			role.setName(m.name());
			role.setDescription(m.description());
			role.setRolePermissions(findPermission);
			role.setLevel(m.level());
			return role;
		}).toList();
		roleRepository.saveAll(list);
		return "Roles Added";
	}

}
