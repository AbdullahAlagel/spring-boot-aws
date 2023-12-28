package com.aws.epl.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aws.epl.demo.dto.UserLoginInfo;
import com.aws.epl.demo.enums.RecordActivityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

	 private static final long serialVersionUID = 1L;

	    private String username;
	    private String password;
	    private Integer badLoginCount, roleLevel, type;
	    private RecordActivityType record;
	    private List<SimpleGrantedAuthority> authorities;

//	    private String roleName;

	    public CustomUserDetails(UserLoginInfo user) {
	        username = user.getUserId();
	        password = user.getPassword();
	        badLoginCount = user.getBadLoginCount();
	        record = user.getRecordActivity();
//	        roleLevel = user.getRoleLevel();
	        type = user.getType();
//	        roleName = user.getRoleName();
	    }

	    @Override
	    public Collection<SimpleGrantedAuthority> getAuthorities() {
	        if (authorities == null)
	            authorities = new ArrayList<>();
	        return authorities;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public String getUsername() {
	        return username;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
//		        return RecordActivityType record ;
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return badLoginCount < 15;
	    }
}
