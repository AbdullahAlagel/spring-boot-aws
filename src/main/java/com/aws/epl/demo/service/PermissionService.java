package com.aws.epl.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aws.epl.demo.dto.CourseInput;
import com.aws.epl.demo.dto.ParentPageDto;
import com.aws.epl.demo.dto.PermissionD;
import com.aws.epl.demo.entity.ParentPage;
import com.aws.epl.demo.entity.Permission;
import com.aws.epl.demo.exception.GeneralException;
import com.aws.epl.demo.repo.ParentPageRepository;
import com.aws.epl.demo.repo.PermissionRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@AllArgsConstructor
public class PermissionService {

	private final PermissionRepository permissionRepository;
	private final ParentPageRepository parentPageRepository;

	public String addNewPermission(PermissionD permissionDto) {
		Permission permission = new Permission();
		permission.setName(permissionDto.getName());
		permission.setOrderList(permissionDto.getOrderList());
		permission.setTag(permissionDto.getTag());
		permission.setUrl(permissionDto.getUrl());
		if (permissionDto.getParentPageDto().getDisplayTag()!=null && permissionDto.getHasParentPage()) {
			log.info("Has Perant Page: {}",permissionDto.getHasParentPage());
			ParentPage parentPage = parentPageRepository.findByDisplayTag(permissionDto.getParentPageDto().getDisplayTag());
			if (parentPage!=null) {
				permission.setParentPage(parentPage);
			}else{
				throw new GeneralException("Must Insert Perant Page");
			}
		}
		permissionRepository.save(permission);
		return "Permission added .";
	}

	public List<Permission> findAllPermission() {
		return permissionRepository.findAll();
	}

	public String deletePermission(Long id) {
		
		return null;
	}

	public String updatePermission(Long id, @Valid CourseInput dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addNewParentPage(ParentPageDto parentPageDto) {
		ParentPage page = new ParentPage();
		page.setDisplayTag(parentPageDto.getDisplayTag());
		page.setDisplayOrder(parentPageDto.getDisplayOrder());
//		List<Permission> list = parentPageDto.getPermissions().stream().map(a -> {
//			Permission permission = new Permission();
//			permission.setName(a.getName());
//			permission.setOrderList(a.getOrderList());
//			permission.setUrl(a.getUrl());
//			permission.setTag(a.getTag());
////			permission.setLoginTime(null);
//			return permission;
//		}).toList();
//		page.setPermissions(list);
		parentPageRepository.save(page);
		return "Page added ";

	}

	public List<ParentPage> findAllPages() {
		return parentPageRepository.findAll();
	}

	public String addNewPermissions(List<PermissionD> permission) {
		List<Permission> permissions = permission.stream().map(p ->{
			Permission permissionInput = new Permission();
			permissionInput.setName(p.getName());
			permissionInput.setOrderList(p.getOrderList());
			permissionInput.setTag(p.getTag());
			permissionInput.setUrl(p.getUrl());
			if (p.getParentPageDto().getDisplayTag()!=null && p.getHasParentPage()) {
				log.info("Has Perant Page: {}",p.getHasParentPage());
				ParentPage parentPage = parentPageRepository.findByDisplayTag(p.getParentPageDto().getDisplayTag());
				if (parentPage!=null) {
					permissionInput.setParentPage(parentPage);
				}else{
					throw new GeneralException("Must Insert Perant Page");
				}
			}
			return permissionInput;
		}).toList();
		permissionRepository.saveAll(permissions);
		return "Permissions added.";
	}

	public String addNewParentPages(List<ParentPageDto> page) {
		List<ParentPage> pageList = page.stream().map(p -> {
			ParentPage pageInput = new ParentPage();
			pageInput.setDisplayTag(p.getDisplayTag());
			pageInput.setDisplayOrder(p.getDisplayOrder());
			return pageInput;
		}).toList();
		parentPageRepository.saveAll(pageList);
		return "Pages added ";
	}
}
