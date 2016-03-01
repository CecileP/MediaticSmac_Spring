package fr.iocean.application.persistence;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConvertDate implements AttributeConverter<LocalDate, Date> {

	public Date convertToDatabaseColumn(LocalDate date) {
		return date == null ? null : Date.valueOf(date);
	}

	public LocalDate convertToEntityAttribute(Date value) {
		return value == null ? null :value.toLocalDate();
	}

	public static LocalDate dateToLocalDate(java.util.Date d) {
		return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static java.util.Date localDateToDate(LocalDate ld) {
		return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
//	public static LocalDate dateToLocalDateSql(java.sql.Date d) {
//		return d.toLocalDate();
//	}
//
//	public static java.sql.Date localDateToDateSql(LocalDate ld) {
//		return Date.valueOf(ld);
//	}
}
