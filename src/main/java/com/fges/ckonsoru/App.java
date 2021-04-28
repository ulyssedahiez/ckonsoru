/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;



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
import java.util.Properties;






/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
	

	
	
	


    public static void main(String args[]) throws ParseException{
        
       /* Client monCli  = new Client(1,"Durant","Paul");
        monCli.info();*/
        
        
        //<DATE ESSAI 
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
        
        
        
        //lundi["8:00","17:00"]
        
        
        
        //System.out.println("Disponibilités pour le " + debut.format(timeFormatter));
        
        /*Veterinaire vet = new Veterinaire(1,"L","Belacqua");
        vet.creationSemaine(1);*/
        /*public Semaine(int id, String firstname, String lastname , Date lundi[] , Date mardi[] , 
    			Date mercredi[], Date jeudi[], Date vendredi[], Date samedi[])*/
        
        //String[] lbLundi = {"15:00","19:00"};
        /*String[] lbMardi = {"8:00","12:00"};
        String[] lbMercredi = {"",""};
        String[] lbJeudi = {"8:00","12:00"};
        String[] lbVendredi = {"",""};
        String[] lbSamedi = {"8:00","12:00"};*/
        
        
        //Semaine maSem = new Semaine(1,"L","Belacqua",lbLundi,lbMardi , lbMercredi, lbJeudi, lbVendredi, lbSamedi) ;
        
       // maSem.info();
        
        
        
        ///creationSemaine(1);
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        // chargement de la configuration de la persistence bonjour bonjour
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));
        
        System.out.println("Actions disponibles : \n"
        + "1: Afficher les créneaux disponibles pour une date donnée\r\n"
        + "2: Lister les rendez-vous passés, présent et à venir d un client\r\n"
        + "3: Prendre un rendez-vous\r\n"
        + "4: Supprimer un rendez-vous\r\n"
        + "9. Quitter");
        
        
        ConnexionBDD test = new ConnexionBDD();
        //String date = "2021-03-18";
       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDateTime dt = LocalDateTime.parse("2021-02-18", formatter);*/
 
        
        //test.lesdispo("2021-02-18");
        //test.rdvClientAfficher("M. Byrnison");
        test.rdvDispo("2021-02-18");
        
    }
    
}
