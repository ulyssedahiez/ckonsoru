package com.fges.ckonsoru.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;



public class AfficherListe {

	
		
		

	public void AfficherListeAffiche(ArrayList<String> ListeRdv) {
		for (int i = 0; i<ListeRdv.size(); i++) {
			
			System.out.println(ListeRdv.get(i));
			
		}
		return;
	}
	
	
}
