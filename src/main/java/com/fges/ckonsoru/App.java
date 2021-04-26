/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;

import java.util.Properties;
import java.sql.Date;
import java.util.Calendar;


/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
	//Date aujourdhui = SystemClockFactory.getDatetime();
	
    public static void main(String args[]){
        
        Client monCli  = new Client("Durant","Paul");
        monCli.info();
        monCli.showVet();
        
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
