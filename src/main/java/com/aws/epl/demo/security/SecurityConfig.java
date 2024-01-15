package com.aws.epl.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.aws.epl.demo.interceptor.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

	private final JwtAuthenticationFilter authFilter;
	private final UserAuthenticationEntryPoint unauthorizedHandler;
	private final UserDetailsService userDetailsService;

	// (securityFilterChain) New method provided by spring security 3
	// replicated method in old spring security less than 2.7
	// we should not store the authentication state or session state (should be
	// STATELESS ) to ensure every request will be authenticated
	// (Authorization) part
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll())
				.headers(headers -> headers.frameOptions().disable())
				.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
				.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/sessions")))
				.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/sign-in")))
				.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/api/v1/forget-password"))
				.permitAll().requestMatchers(new AntPathRequestMatcher("/api/v1/sessions", HttpMethod.POST.name()))
				.permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/v1/sign-in", HttpMethod.POST.name()))
				.permitAll()
//				.requestMatchers(new AntPathRequestMatcher("/api/v1/resend-otp",HttpMethod.POST.name())).permitAll()
//				.requestMatchers(new AntPathRequestMatcher("/api/v1/verify-otp",HttpMethod.POST.name())).permitAll()
//				.requestMatchers(new AntPathRequestMatcher("/api/v1/")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/v1/health-check")).permitAll().anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.headers(headers -> headers.referrerPolicy(referrer -> referrer.policy(ReferrerPolicy.NO_REFERRER)))
				.authenticationProvider(authenticationProvider())
				// (addFilterBefore) we need to execute this filter before called user name and
				// password authentication
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors().and().csrf().disable();
		return http.build();
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {

		final UserAuthenticationProvider authProvider = new UserAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}

//class ShaPasswordEncoder extends MessageDigestPasswordEncoder {
//	public ShaPasswordEncoder() {
//		super("SHA-1");
//	}
//}
