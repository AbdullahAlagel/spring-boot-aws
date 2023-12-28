package com.aws.epl.demo.core;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import com.aws.epl.demo.utils.Language;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocaleService {

	private final MessageSource messageSource;

	public String getMessage(Language lang, String key, String... values) {
		try {
			return messageSource.getMessage(key, values, lang.locale);
		} catch (NoSuchMessageException e) {
			return key;
		}
	}

	public String getMessage(Language lang, String key) {
		try {
			return messageSource.getMessage(key, null, lang.locale);
		} catch (NoSuchMessageException e) {
			return key;
		}
	}

}