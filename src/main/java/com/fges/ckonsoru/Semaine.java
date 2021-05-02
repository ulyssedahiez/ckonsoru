package com.fges.ckonsoru;


public class Semaine extends Veterinaire {
	public int id;
	public String Lundi[];
	public String Mardi[];
	public String Mercredi[];
	public String Jeudi[];
	public String Vendredi[];
	public String Samedi[];
	
	
	public Semaine(int id, String firstname, String lastname , String[]  lundi, String[] mardi , 
			String[] mercredi,String[] jeudi,String[] vendredi, String[] samedi) {
		super(id, firstname, lastname);
		// TODO Auto-generated constructor stub
		this.Lundi = lundi;
		this.Mardi = mardi;
		this.Mercredi = mercredi;
		this.Jeudi = jeudi;
		this.Vendredi = vendredi;
		this.Samedi = samedi;
		
	}
	
	
	public void info() {
		System.out.println("Horaire de " + this.prenom + "." + this.nom );
		System.out.println("Horaire Lundi :" + this.Lundi[0]  + " - "  + this.Lundi[1] + " \n" + 
				"Horaire Mardi : " + this.Mardi[0] + " - "  + this.Mardi[1] + " \n" +
				"Horaire Mercredi : " + this.Mercredi[0] + " - "  + this.Mercredi[1] + " \n" +
				"Horaire Jeudi : " + this.Jeudi[0] + " - "  +  this.Jeudi[1] + " \n" +
				"Horaire Vendredi : " + this.Vendredi[0] + " - "  + this.Vendredi[1] + " \n" +
				"Horaire Samedi : " + this.Samedi[0]  + " - "  +  this.Samedi[1]+ " \n" );
	}
	
}
