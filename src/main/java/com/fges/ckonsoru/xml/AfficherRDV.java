package com.fges.ckonsoru.xml;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.fges.ckonsoru.metier.Client;
import com.fges.ckonsoru.metier.RDV;

public class AfficherRDV {
	ConnexionXmlDAO dataXml = new ConnexionXmlDAO();
	File file = dataXml.getFile("ckonsoru.xml");

	
	public ArrayList<RDV> getAllRDVAfficher(Client Client) throws SAXException, IOException, ParserConfigurationException {
		LocalDateTime rienDate = null;
		ArrayList<RDV> RendezVous = new ArrayList<RDV>();
		
		RDV Complet = new RDV(rienDate, "veto", Client);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db;
		
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file); 
			doc.getDocumentElement().normalize();  
			
			NodeList RDVC = doc.getElementsByTagName("rdv");
			Node DebutRendezVous = (Node) RDVC.item(0).getChildNodes();
			
			
			
			String client;
			//String ligne = null;
			for (int i = 0; i <RDVC.getLength() ; i++) {
				
				
				
				DebutRendezVous = (Node) RDVC.item(i).getChildNodes();
				
				client = DebutRendezVous.getTextContent().toString();
				
				String[] parts = client.split("\\n");
				String datedure = parts[1];
				String clientdure = parts[2];
				String vetodure = parts[3];
				
				
				if(clientdure.equals(Client.getNom())) {
					
				 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 LocalDateTime debut1 = LocalDateTime.parse(datedure, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
					
					Complet = new RDV(debut1 ,vetodure, Client);
					//System.out.println(Complet);
					RendezVous.add(Complet);
		        }
			}
						
			
		
		
		
		return RendezVous;
	  
	}
	
	
	
	
}
