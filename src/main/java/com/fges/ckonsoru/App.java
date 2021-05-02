
package com.fges.ckonsoru;
import java.text.ParseException;
import java.util.Properties;


public class App {

    public static void main(String args[]) throws ParseException{
      
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();



        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));


        GestionAction maGestion = new GestionAction();
        maGestion.doAction();
        
        



    }
    
}
