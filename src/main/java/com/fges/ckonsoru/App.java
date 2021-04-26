/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;



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
        
        Client monCli  = new Client(1,"Durant","Paul");
        monCli.info();
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
        
        System.out.println("Disponibilités pour le " + debut.format(timeFormatter));

        
      // Semaine maSem = new Semaine()
      
        
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        // chargement de la configuration de la persistence bonjour bonjour
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));
    }
    
}
