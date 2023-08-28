package com.hrms.entities;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getGender();
	}

	@Override
	public Gender convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return Stream.of(Gender.values()).filter(g -> g.getGender().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
