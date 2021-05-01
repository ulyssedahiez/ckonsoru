package com.fges.ckonsoru;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;  
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;  

public class ConnexionXML {
	File file = new File("C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml");
	
	public void searchDidponibilite (LocalDateTime Date) throws SAXException, IOException {
		
	}
	public ArrayList<String> getDisponibilites(String veterinaire, LocalDateTime Date) throws SAXException, IOException {
		int Yout = 0;
		
		ArrayList<String> Disponibilites = new ArrayList<String>();
		
		 String LeJour = Date.toString().substring(8, 10);
	        String LeMois = Date.toString().substring(5, 7);
	        String LAnnee = Date.toString().substring(0,4);
	       
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file); 
			doc.getDocumentElement().normalize();  
			NodeList NodeList = doc.getElementsByTagName("debut");
			
			
			NodeList Journee = doc.getElementsByTagName("jour");
			
			
			for (int i = 0; i <Journee.getLength() ; i++) {
				
				
				
					if(Journee.item(i).getTextContent().equals(this.getDay(Date))) {
						
						
						String Dispo = doc.getElementsByTagName("debut").item(i).getTextContent();
						String Veterinaire = doc.getElementsByTagName("veterinaire").item(i).getTextContent();
						String Heure = "";
						String Minutes = "";
						Yout = 0;
					do {

						
						Heure = Dispo.substring(0,2);
						Minutes = Dispo.substring(3,5);
						
						if(Yout != 0) {
						Minutes = String.valueOf(Integer.parseInt (Minutes) + 20);
						
						}else {Yout = 1;}
		
						if(Minutes.equals("60")) {
							Minutes = "00";
							
							Heure = String.valueOf(Integer.parseInt (Heure) + 1);
							
							if(Integer.parseInt (Heure) < 10) {
								Heure = "0" + Heure;
							}
						}
						
						Dispo = Heure+":"+ Minutes;
						String Ligne = Veterinaire + " : " + LeJour+"/"+LeMois+"/"+LAnnee + " " + Dispo;
						if(Veterinaire.equals(veterinaire)){
							
						Disponibilites.add(Ligne);
						
						
					}
				    } while (Dispo.equals(doc.getElementsByTagName("fin").item(i).getTextContent()) == false);
						
					}
			} 
				
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Disponibilites.remove(Disponibilites.size()-1);
		System.out.println(Disponibilites);
		return Disponibilites;
		
	}
	
	public ArrayList<String> getRDVVeto(String veterinaire, LocalDateTime dateRDV) {
		
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
			
			
			
			String veto;
			String ligne = null;
			for (int i = 0; i <RDV.getLength() ; i++) {
				
				
				
				DebutRendezVous = (Node) RDV.item(i).getChildNodes();
				
				veto = DebutRendezVous.getTextContent().toString();
				
				String[] parts = veto.split("\\n");
				String datedure = parts[1];
				String clientdure = parts[2];
				String vetodure = parts[3];
				
				
				if(vetodure.equals(veterinaire)) {
				
				String LeJour = datedure.substring(8, 10);
		        String LeMois = datedure.substring(5, 7);
		        String LAnnee = datedure.substring(0,4);
		        String Heure = datedure.substring(11,13);
				String Minutes = datedure.substring(14,16);
				
				String Complet = vetodure+" : "+LeJour+"/"+LeMois+"/"+LAnnee+" "+Heure+":"+Minutes;
				//System.out.println(Complet);
				RendezVous.add(Complet);
				}
			}
			
						
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return RendezVous;
	}
	
	public String getDay(LocalDateTime dateDispo) {
		int dayofweek = dateDispo.getDayOfWeek().getValue();
		String jour = "";
		if(dayofweek == 1) {
			jour = "lundi";
		}
		if(dayofweek == 2) {
			jour = "mardi";
		}
		if(dayofweek == 3) {
			jour = "mercredi";
		}
		if(dayofweek == 4) {
			jour = "jeudi";
		}
		if(dayofweek == 5) {
			jour = "vendredi";
		}
		if(dayofweek == 6) {
			jour = "samedi";
		}
		if(dayofweek == 7) {
			jour = "dimanche";
		}
		return jour;
	}
	
	
	
	public void mainXml () throws ParserConfigurationException, SAXException, IOException {
		
	 
		
	
	
	
	
	
	}
	
}

