package com.fges.ckonsoru;

import java.sql.Date;

public class Semaine extends Veterinaire {
	public int id;
	public Date dateLundi[];
	public Date dateMardi[];
	public Date dateMercedi[];
	public Date dateJeudi[];
	public Date dateVendredi[];
	public Date dateSamedi[];
	
	
	public Semaine(int id, String firstname, String lastname , Date lundi[] , Date mardi[] , 
			Date mercredi[], Date jeudi[], Date vendredi[], Date samedi[]) {
		super(id, firstname, lastname);
		// TODO Auto-generated constructor stub
		this.dateLundi = lundi;
		this.dateMardi = mardi;
		this.dateMercedi = mercredi;
		this.dateJeudi = jeudi;
		this.dateVendredi = vendredi;
		this.dateSamedi = samedi;
		
	}
	
}
