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
import java.sql.Date;
import java.util.Calendar;




/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
<<<<<<< HEAD
	//Date aujourdhui = SystemClockFactory.getDatetime();
	
    public static void main(String args[]){
=======
	
	

    public static void main(String args[]) throws ParseException{
>>>>>>> main
        
        Client monCli  = new Client(1,"Durant","Paul");
        monCli.info();
<<<<<<< HEAD
        monCli.showVet();
=======
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
        
        System.out.println("Disponibilités pour le " + debut.format(timeFormatter));

        
      // Semaine maSem = new Semaine()
      
>>>>>>> main
        
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        System.out.println(System.currentTimeMillis());
        System.out.println(new java.util.Date());
        System.out.println(Calendar.getInstance().getTimeInMillis() );
        System.out.println(Calendar.getInstance().getTime().getTime ());
        
        // chargement de la configuration de la persistence
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));
    }
    
}
