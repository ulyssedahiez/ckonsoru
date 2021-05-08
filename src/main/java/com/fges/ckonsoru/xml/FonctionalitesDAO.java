package com.fges.ckonsoru.xml;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class FonctionalitesDAO {
	Disponibilites dispo = new Disponibilites();
	AfficherRDV affi = new AfficherRDV();
	AjouterRDV add = new AjouterRDV();
	SupprimerRDV suppr = new SupprimerRDV();
	AfficherListe affich = new AfficherListe();
	
	public ArrayList<String> getRDVVeto( LocalDateTime dateRDV) throws SAXException, IOException{
		
		return dispo.getAllDidponibiliteDispo(dateRDV);
	}
	
	public ArrayList<String> getAllRDV(String client) throws SAXException, IOException{
		
		return affi.getAllRDVAfficher(client);
	}
	
	public void addRDV(String Veterinaire, String Date, String Client) throws SAXException, TransformerException {
		
		add.addRDVAjouter(Veterinaire,  Date, Client);
		return;
	}
	
	public void SupprRdv(String Date, String Client) throws TransformerException, SAXException {
		
		suppr.SupprRdvSuppr( Date, Client);
		return;
	}
	
	public void AfficherList(ArrayList<String> ListeRdv){
		
		affich.AfficherListeAffiche(ListeRdv);
		return;
	}
	
	
	
	
	
}
