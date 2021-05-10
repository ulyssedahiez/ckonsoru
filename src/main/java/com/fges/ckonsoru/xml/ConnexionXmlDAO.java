package com.fges.ckonsoru.xml;

import java.io.File;


public class ConnexionXmlDAO {

	private File fileckonsoru = new File(".\\src\\main\\resources\\ckonsoru.xml");
	
	public File getFile(String fichier) {
		
		if(fichier.equals("ckonsoru.xml")) {
		return fileckonsoru;
		}
		
		else {
			return null;
		}
		//connexion pour choisir le xml si on veut changer de fichier.
	}
}
