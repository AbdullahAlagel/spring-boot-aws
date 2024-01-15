package com.aws.epl.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.epl.demo.entity.ParentPage;

@Repository
public interface ParentPageRepository extends JpaRepository<ParentPage, Long>{

	ParentPage findByDisplayTag(String displayTag);

}
