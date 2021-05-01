/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;




import java.text.ParseException;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;






/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
	

	
	
	


    public static void main(String args[]) throws ParseException{
      
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();


        LocalDateTime rightNow = LocalDateTime.now();



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

        int  numero = 0;
        ConnexionBDD test = null;
        Scanner scanIn = null;

        try{

            scanIn = new Scanner(System.in);
            numero = scanIn.nextInt();
            test = new ConnexionBDD();
        }catch(InputMismatchException e){
            System.out.println("Veullez rentrer un chiffre !");
            System.exit(0);

        }
    
     
       while(numero != 9){

        
            
            switch(numero) {
                case 1:
                    
                    String explicationStrDispo = "Entrer une date au format JJ/MM/AAAA (ex: 18/03/2021) :";
                    System.out.println(explicationStrDispo);
                    
                    Scanner scannCrenaux = new Scanner(System.in);
                    String DateIn =  scannCrenaux.nextLine();
                    


                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime debut = LocalDateTime.parse(DateIn+" 01:00", timeFormatter);



                    boolean isBefore = debut.isBefore(rightNow);
                    
                    while(isBefore){
                        System.out.println("Veuillez choisir une date ou une horaire future et non passé ");
                        System.out.println(choix);
                        System.out.println("Entrer un numéro d action:");
                        numero = scanIn.nextInt();
                         
                        //String explicationStrDispo = "Entrer une date au format JJ/MM/AAAA (ex: 18/03/2021) :";
                        System.out.println(explicationStrDispo);
                        
                        scannCrenaux = new Scanner(System.in);
                        DateIn =  scannCrenaux.nextLine();
                        


                        timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        debut = LocalDateTime.parse(DateIn+" 01:00", timeFormatter);

                        isBefore = debut.isBefore(rightNow);
                    }



                  
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



                    //de la 
                    timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    debut = LocalDateTime.parse(dateRdv, timeFormatter);



                    isBefore = debut.isBefore(rightNow);

                    while(isBefore){
                        System.out.println("Veuillez choisir une date ou une horaire future et non passé ");
                        System.out.println(explicationStrPriseRdv);
                        dateRdv =  scanDateRdv.nextLine();

                        timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        debut = LocalDateTime.parse(dateRdv, timeFormatter);

                        isBefore = debut.isBefore(rightNow);




                    }


                    // a la 




                    System.out.println("Indiquer le nom du vétérinaire" );                   
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
                    String explicationSupprimerRdv = "Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)";
                    System.out.println(explicationSupprimerRdv);
                    
                    Scanner scanDateCliSupprRDV = new Scanner(System.in);
                    String DateRdvsupp = scanDateCliSupprRDV.nextLine();


                    timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    debut = LocalDateTime.parse(DateRdvsupp, timeFormatter);



                    isBefore = debut.isBefore(rightNow);

                    while(isBefore){
                        System.out.println("Veuillez choisir une date ou une horaire future et non passé ");
                        System.out.println(explicationSupprimerRdv);
                        DateRdvsupp = scanDateCliSupprRDV.nextLine();

                        timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        debut = LocalDateTime.parse(DateRdvsupp, timeFormatter);

                        isBefore = debut.isBefore(rightNow);




                    }


                    System.out.println("Indiquer le nom du client");
                    Scanner scanNomCliSupprRDV = new Scanner(System.in);
                    String nomCliRdvsupp = scanNomCliSupprRDV.nextLine();


                    //String essai = "27/05/2021 08:20";
                    
                    test.supprRdv(DateRdvsupp ,nomCliRdvsupp );
                    
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

        
        
        
    }
    
}
