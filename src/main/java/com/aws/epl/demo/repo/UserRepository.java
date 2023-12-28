package com.aws.epl.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.aws.epl.demo.dto.UserLoginInfo;
import com.aws.epl.demo.entity.User;
import com.aws.epl.demo.enums.RecordActivityType;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "select new com.aws.epl.demo.dto.UserLoginInfo"
			+ "(u.id,u.type,u.userId,u.username,u.userCreationDate,u.badLoginCount,u.lastLoginTime,u.recordActivity,u.password)"
			+ "from User u where u.userId = :username")
	Optional<UserLoginInfo> findLoginUserInfo(String username);
	
	
	
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query("update User u set u.badLoginCount = 0 WHERE u.username = :username and recordActivity = :recordActivity")
	int resetBadLogin(@Param("username") String username, @Param("recordActivity") RecordActivityType recordActivity);

//	@Modifying(clearAutomatically = true, flushAutomatically = true)
//	@Query(nativeQuery = true, value = "UPDATE TBL_USERS SET BAD_LOGIN_COUNT=BAD_LOGIN_COUNT+1 , "
//			+ " RECORD_ACTIVITY = CASE WHEN  (BAD_LOGIN_COUNT+1)>= 15 THEN 1 ELSE RECORD_ACTIVITY END  WHERE [USER_ID] =?1 AND RECORD_ACTIVITY=0 ")
//	int incrementBadLogin(String username);
	
}
