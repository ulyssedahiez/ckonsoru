package com.fges.ckonsoru;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.xpath.XPathExpression;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;


public class SupprimerRDV {
	File file = new File("C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml");
	public void SupprRdv(String Date, String Client) throws TransformerException, SAXException {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		//List<RendezVous> rdvs = new LinkedList<>();
		
		try {
			// charger le fichier xml
			
			builder = factory.newDocumentBuilder();
			String filepath = "C:\\Users\\dahie\\Documents\\SDN-S6\\design_pattern\\ckonsoru\\src\\main\\resources\\ckonsoru.xml";
			Document xmldoc = builder.parse(filepath);
			NodeList RDV = xmldoc.getElementsByTagName("rdv");

			String LeJour = Date.substring(0,2);
	        String LeMois = Date.substring(3,5);
	        String LAnnee = Date.substring(6,10);
	        String Heure = Date.substring(11,13);
			String Minutes = Date.substring(14,Date.length());
			String LaDate = LAnnee+"-"+LeMois+"-"+LeJour+"T"+Heure+":"+Minutes+":00";
			
		
			
			for (int i = 0; i < RDV.getLength(); i++) {
				Element product = (Element) RDV.item(i);
				Element dateTag = (Element) product.getElementsByTagName("debut").item(0);
				Node clientTag = product.getElementsByTagName("client").item(0);
				System.out.println("ouais "+i+" : "+ clientTag.getParentNode().getTextContent());
				if (dateTag.getTextContent().equalsIgnoreCase(LaDate) && clientTag.getTextContent().equalsIgnoreCase(Client)) {
					System.out.println("ouais 0 : "+ clientTag.getParentNode().getParentNode().getTextContent());
					clientTag.getParentNode().getParentNode().removeChild(RDV.item(i));
					
					System.out.println("ouais 1 : "+ clientTag.getParentNode().getTextContent());
				}
			}
			

			
			// enregistrer le fichier
				
				DOMSource source = new DOMSource(xmldoc);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = null;
				try {
					
					transformer = transformerFactory.newTransformer();
					/*transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");*/
					

				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				StreamResult result = new StreamResult(filepath);
				transformer.transform(source, result);
		} catch (IOException | ParserConfigurationException e) {
		e.printStackTrace(System.err);
		}
	}


}
