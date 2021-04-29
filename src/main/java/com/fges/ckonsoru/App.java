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
import java.util.Scanner;






/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
	

	
	
	


    public static void main(String args[]) throws ParseException{
        
       /* Client monCli  = new Client(1,"Durant","Paul");
        monCli.info();*/
        
        
        //<DATE ESSAI 
        /*DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("18/03/2021 11:50", timeFormatter);*/
        
        
        
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
        




        String choix = "Actions disponibles : \n"
        + "1: Afficher les créneaux disponibles pour une date donnée\r\n"
        + "2: Lister les rendez-vous passés, présent et à venir d un client\r\n"
        + "3: Prendre un rendez-vous\r\n"
        + "4: Supprimer un rendez-vous\r\n"
        + "9. Quitter";
        
        System.out.println(choix);
        System.out.println("Entrer un numéro d action:");

        

        Scanner scanIn = new Scanner(System.in);
        int numero = scanIn.nextInt();
        ConnexionBDD test = new ConnexionBDD();
        while(numero != 9){

        
            
            switch(numero) {
                case 1:
                    
                    String explicationStrDispo = "Entrer une date au format JJ/MM/AAAA (ex: 18/03/2021) :";
                    System.out.println(explicationStrDispo);
                    
                    Scanner scannCrenaux = new Scanner(System.in);
                    String DateIn =  scannCrenaux.nextLine();
                    

                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime debut = LocalDateTime.parse(DateIn+" 11:50", timeFormatter);
                    test.AffichageDispoCorrect(test.dispoAllVet(debut) , debut);
                    //scannCrenaux.close();

                    System.out.println(choix);
                    System.out.println("Entrer un numéro d action:");
                    numero = scanIn.nextInt();
                break;
                case 2:
                    String explicationStrRdvCli = "Affichage des rendez-vous d un client \n" + "Indiquer le nom du client";
                    System.out.println(explicationStrRdvCli);
                    Scanner scanNom = new Scanner(System.in);
                    String nomCli =  scanNom.nextLine();
                    test.rdvClientAfficher(nomCli);

                    System.out.println(choix);
                    System.out.println("Entrer un numéro d action:");
                    numero = scanIn.nextInt();
                break;
                case 3:
                    String explicationStrPriseRdv = "Prise de rendez-vous \n" + "Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)";
                    System.out.println(explicationStrPriseRdv);
                    
                    Scanner scanDateRdv = new Scanner(System.in);
                    String dateRdv =  scanDateRdv.nextLine();

                    System.out.println("ndiquer le nom du vétérinaire" );                   
                    Scanner scanNomRdv = new Scanner(System.in);
                    String nomVetRdv =  scanNomRdv.nextLine();

                    System.out.println("Indiquer le nom du client");
                    Scanner scanNomCliRdv = new Scanner(System.in);
                    String nomCliRdv =  scanNomCliRdv.nextLine();
                    
                    
                    //String essai = "27/05/2021 08:20";
                    //test.priseRdv(essai,"L. Belacqua","B. Costa");
                    test.priseRdv(dateRdv,nomVetRdv,nomCliRdv);
                    System.out.println(choix);
                    System.out.println("Entrer un numéro d action:");
                    numero = scanIn.nextInt();
                break;
                case 4:
                    String explicationSupprimerRdv = "Affichage des rendez-vous d un client \n" + "Indiquer le nom du client";
                    System.out.println(explicationSupprimerRdv);
                    
                    System.out.println(choix);
                    System.out.println("Entrer un numéro d action:");
                    numero = scanIn.nextInt();
                break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Excusez moi je n'ai pas compris \n");
                    System.out.println(choix);
                    System.out.println("Entrer un numéro d action:");
                    numero = scanIn.nextInt();
            }

        }
        scanIn.close();

        
        //System.out.println(sWhatever);
        
        
    }
    
}
