package com.aws.epl.demo.utils;

import java.util.Locale;

import jakarta.persistence.AttributeConverter;

public enum Language {
	
	ENGLISH("en"), ARABIC("ar");

	public Locale locale;
	public String language;

	Language(String localeStr) {
		this.language = localeStr;
		this.locale = new Locale(localeStr);
	}

	public static Language languageOf(String localeStr) {
		for (Language lang : values()) {
			if (lang.locale.getLanguage().equalsIgnoreCase(localeStr)) {
				return lang;
			}
		}
		return Language.ARABIC;
	}
	
	public static Language languageOf(Short languageCode) {
		if(languageCode.equals((short) 2)) {
			return Language.ENGLISH;
		}
		else {
			return Language.ARABIC;
		}
	}
	
	@jakarta.persistence.Converter
	public static class Converter implements AttributeConverter<Language, String> {

		@Override
		public String convertToDatabaseColumn(Language attribute) {
			if (attribute == null)
				return null;
			return attribute.language;
		}

		@Override
		public Language convertToEntityAttribute(String dbData) {
			for (Language e : values()) {
				if (e.language.equalsIgnoreCase(dbData)) {
					return e;
				}
			}
			//default value
			return Language.ARABIC;
		}
	}
	
	
}
