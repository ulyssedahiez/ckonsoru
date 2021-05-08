package com.fges.ckonsoru.xml;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class AfficherRDV {
	ConnexionXmlDAO dataXml = new ConnexionXmlDAO();
	File file = dataXml.getFile("ckonsoru.xml");

	
	public ArrayList<String> getAllRDVAfficher(String Client) throws SAXException, IOException {
		
		ArrayList<String> RendezVous = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file); 
			doc.getDocumentElement().normalize();  
			
			NodeList RDV = doc.getElementsByTagName("rdv");
			Node DebutRendezVous = (Node) RDV.item(0).getChildNodes();
			
			
			
			String client;
			//String ligne = null;
			for (int i = 0; i <RDV.getLength() ; i++) {
				
				
				
				DebutRendezVous = (Node) RDV.item(i).getChildNodes();
				
				client = DebutRendezVous.getTextContent().toString();
				
				String[] parts = client.split("\\n");
				String datedure = parts[1];
				String clientdure = parts[2];
				String vetodure = parts[3];
				
				
				if(clientdure.equals(Client)) {
			
		        
		        	String LeJour = datedure.substring(8,10);
			        String LeMois = datedure.substring(5,7);
			        String LAnnee = datedure.substring(0,4);
			        String Heure = datedure.substring(11,13);
					String Minutes = datedure.substring(14,16);
					
					String Complet = LeJour+"/"+LeMois+"/"+LAnnee+" "+Heure+":"+Minutes+" avec "+ vetodure;
					System.out.println(Complet);
					RendezVous.add(Complet);
		        }
			}
						
			
		} catch (Exception e) {
		
		}
		
		
		return RendezVous;
	  
	}
	
}
