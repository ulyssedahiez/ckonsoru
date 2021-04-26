package com.fges.ckonsoru;

import java.sql.Date;

public class Meeting extends Client {
	public int id;
	public Date dateJour;
	public Date duree[];
	public boolean statut;

	
	public Meeting(int id, String name, String firstname , Date maDate , Date duree[] , boolean status) {
		super(id, name, firstname);
		// TODO Auto-generated constructor stub	
		this.dateJour = maDate;
		this.duree = duree;
		this.statut = status;
	}
	
}




/*
private AnotherClass mClass;

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mClass = new AnotherClass(this);
}

//Implement each method you want to use.
public String getInfoFromOtherClass()
{
   return mClass.getInfoFromOtherClass();
}*/