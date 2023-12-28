package com.aws.epl.demo.security;


import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.aws.epl.demo.core.LocaleService;
import com.aws.epl.demo.utils.Language;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private LocaleService localeService;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		final String expiredMsg = (String) request.getAttribute("token.expired");
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(401);
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("timestamp", LocalDateTime.now().toString());
		node.put("status", 401);
		node.put("error", "Unauthorized");
		node.put("message", localeService.getMessage(Language.languageOf(LocaleContextHolder.getLocale().getLanguage()), expiredMsg!=null? expiredMsg : (String) request.getAttribute("invalid.token")));
		node.put("path", request.getRequestURI());
		response.getWriter().write(node.toString());

	}
}
