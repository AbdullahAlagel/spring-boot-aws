package com.aws.epl.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.epl.demo.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	
	List<Permission> findByName(String name);

	List<Permission> findByNameIn(List<String> list);
}
