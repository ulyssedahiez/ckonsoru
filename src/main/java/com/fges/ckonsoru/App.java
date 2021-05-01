/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;



import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;






/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
	//Date aujourdhui = SystemClockFactory.getDatetime();
	

	
	
	


    public static void main(String args[]) throws ParseException, ParserConfigurationException, SAXException, IOException{
   
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        
        // chargement de la configuration de la persistence
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));
        




        String choix = "Actions disponibles : \n"
        + "1: Afficher les créneaux disponibles pour une date donnée\r\n"
        + "2: Lister les rendez-vous passés, présent et à venir d un client\r\n"
        + "3: Prendre un rendez-vous\r\n"
        + "4: Supprimer un rendez-vous\r\n"
        + "9. Quitter";
        
        System.out.println(choix);
        System.out.println("Entrer un numéro d action:");

        
        ConnexionXML test = new ConnexionXML();
        //test.mainXml();
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("18/03/2021 11:50", timeFormatter);
        
        test.getDisponibilites("L. Asriel", debut);
        
        //System.out.println(test.getDay(debut));
        
        
        
        //System.out.println(test.getRDVVeto("L. Asriel", debut));
        
        
        
        
    }
    
}
