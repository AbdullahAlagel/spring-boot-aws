package com.aws.epl.demo.enums;

import jakarta.persistence.AttributeConverter;

public enum RecordActivityType {
	ACTIVE, NOT_ACTIVE;

	public Integer code;


	public static RecordActivityType forName(int input) {
		for (RecordActivityType e : values()) {
			if (e.code == input) {
				return e;
			}
		}
		return null;
	}
	
	
	@jakarta.persistence.Converter
	public static class Converter implements AttributeConverter<RecordActivityType, Integer> {

		@Override
		public Integer convertToDatabaseColumn(RecordActivityType attribute) {
			if (attribute == null)
				return ACTIVE.ordinal();
			return attribute.ordinal();
		}

		@Override
		public RecordActivityType convertToEntityAttribute(Integer dbData) {
			if (dbData != null)
				for (RecordActivityType e : values()) {
					if (e.ordinal() == dbData.intValue()) {
						return e;
					}
				}
			return null;
		}
	}
}
