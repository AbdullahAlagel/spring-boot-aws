package com.aws.epl.demo.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.aws.epl.demo.utils.Language;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class LocaleLanguageResolver extends AcceptHeaderLocaleResolver {
	
	private static final List<Locale> SUPPORTED_LOCALES = Arrays.asList(Language.ENGLISH.locale, Language.ARABIC.locale);

	@Bean
	LocaleResolver localeResolver() {
	    AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
	    localeResolver.setSupportedLocales(SUPPORTED_LOCALES);
	    localeResolver.setDefaultLocale(Language.ARABIC.locale);
	    return localeResolver;
	}
	
	public Locale resolveLocale(HttpServletRequest request) {
		if (StringUtils.isEmpty(request.getHeader("Accept-Language"))) {
			return Language.ARABIC.locale;
		}
		return Language.languageOf(request.getHeader("Accept-Language")).locale;
	}
}