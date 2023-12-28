package com.aws.epl.demo.interceptor;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aws.epl.demo.security.JWTBuildService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// this is spring boot 3 changes (OncePerRequestFilter) filter.
	private final JWTBuildService jWTBuildService;
//	private final PermissionRepository permissionRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username = null;
		String jwt = parseJwt(request);
		if (jwt != null) {
			try {
				username = jWTBuildService.extractUsername(jwt);
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
				request.setAttribute("invalid.token", "invalid.token");
			} catch (SignatureException e) {
				log.error("Invalid Token");
				request.setAttribute("invalid.token", "invalid.token");
			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
				request.setAttribute("token.expired", "token.Expire");
			}
		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// if token is valid configure Spring Security to manually set
			// authentication
			if (jWTBuildService.validateToken(jwt, username)) {
				getUserDetails(jwt, username, request);

			}
		}
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
			return headerAuth.substring(7, headerAuth.length());
		log.warn("REQUEST WITH OUT  HEADER '** Authorization ***' ");
		return null;
	}

	/*
	 * All User Details to SecurityContextHolder
	 */
	private void getUserDetails(String jwt, String username, HttpServletRequest request) {

		UserDetails userDetails = jWTBuildService.getUserDetails(jwt);
//		List<String> authotities = permissionRepo.findUserPermission(username);

//		List<SimpleGrantedAuthority> authList = authotities.stream().map(au -> new SimpleGrantedAuthority(au)).toList();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
				null, null);
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

	}

}