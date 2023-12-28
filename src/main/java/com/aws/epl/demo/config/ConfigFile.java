package com.aws.epl.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aws.epl.demo.utils.Language;

import java.time.Instant;
@Configuration
public class ConfigFile {

	@Bean
	MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:/i18n/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setDefaultLocale(Language.ARABIC.locale);
	    return messageSource;
	}


	@Bean
	WebMvcConfigurer webMvcConfigurer(ReqResLogging interceptor) {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(interceptor).addPathPatterns("/**");
			}
		};
	}
}

@Slf4j
@Component
class ReqResLogging implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		// TODO :: set Activate local Arabic
//		LocaleContextHolder.setLocale(new Locale("ar"));
		long startTime = Instant.now().toEpochMilli();
//		log.debug("Request ::({}) With Params:: ({}) :: START At=({}) ", request.getRequestURL().toString(),
//				request.getParameterMap(), Instant.now());
		log.info("###### Request ::({}) With Params:: ({}) :: START At=({}) ", request.getRequestURI().toString(),
				request.getParameterMap(), Instant.now());
		request.setAttribute("RequestTimeStart", startTime);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
								Exception ex) {

		long startTime = (Long) request.getAttribute("RequestTimeStart");
//		log.debug("Request URL:: ({}) :: Time Taken= ({})", request.getRequestURL().toString(),
//				(Instant.now().toEpochMilli() - startTime));
		log.info("###### Request URL:: ({}) :: Time Taken= ({}) MS", request.getRequestURI().toString(),
				(Instant.now().toEpochMilli() - startTime));
	}
}
