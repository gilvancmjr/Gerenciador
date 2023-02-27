package com.monteiro.gerenciador.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class ConverteStringEmLocalDate {
	
	public static LocalDate localDate(String data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(data, formatter);
		return localDate;
		
	}

}
