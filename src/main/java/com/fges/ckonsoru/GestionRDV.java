package com.fges.ckonsoru;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestionRDV {

	// <DATE ESSAI
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);

}
