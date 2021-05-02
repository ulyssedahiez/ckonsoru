package com.fges.ckonsoru;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.xpath.XPathExpression;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;


public class SupprimerRDV {
	File file = new File("C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml");
	public void SupprRdv(String Date, String Client) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db;
		DocumentBuilder builder;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc1 = db.parse(file); 
			doc1.getDocumentElement().normalize();  
			NodeList RDV = doc1.getElementsByTagName("rdv");
			
			String LeJour = Date.substring(0,2);
	        String LeMois = Date.substring(3,5);
	        String LAnnee = Date.substring(6,10);
	        String Heure = Date.substring(11,13);
			String Minutes = Date.substring(14,Date.length());
			String LaDate = LAnnee+"-"+LeMois+"-"+LeJour+"T"+Heure+":"+Minutes+":00";
			
			// charger le fichier xml
			builder = factory.newDocumentBuilder();
			String filepath = "C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml";
			Document xmldoc = builder.parse(filepath);
			// créer la requête XPATH
			String requeteXPATH = "/ckonsoru/rdvs/rdv[starts-with((debut,'"+LaDate+"'), (client, '"+Client+"')]";
			XPath xpath = XPathFactory.newInstance().newXPath();
			javax.xml.xpath.XPathExpression expr = xpath.compile(requeteXPATH);
			// evaluer la requête XPATH
			NodeList nodes = (NodeList) expr.evaluate(xmldoc, XPathConstants.NODESET);

			System.out.println(requeteXPATH);	
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
