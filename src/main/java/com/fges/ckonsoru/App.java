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
	
	public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();
	
    public String url = prop.getProperty("bdd.url");
    public String login = prop.getProperty("bdd.login");
    public String mdp = prop.getProperty("bdd.mdp");
    
	
	
	
	
	public  void creationSemaine(int id_vet){
		//Timestamp timestamp = Timestamp.valueOf("10:10:10");
        //System.out.println(timestamp);
		Connection conn1 = null;        
        try{
        	
            conn1 = DriverManager.getConnection(this.url , this.login,this.mdp);
            System.out.println("Connected to database #1");   
            
            
            
            String sql = "SELECT * FROM disponibilite where id="+id_vet+"";
            
            Statement statement = conn1.createStatement();
  
            ResultSet resultat = statement.executeQuery(sql);
            while (resultat.next()) {
            	int id = resultat.getInt("vet_id");
            	int jours = resultat.getInt("dis_jour");
            	Timestamp heureDebut = resultat.getTimestamp("dis_debut");
            	Timestamp heureFin = resultat.getTimestamp("dis_fin");
            	System.out.println(" ID du vet  ->" + id + "\n"  + ""
            	
            			
            			);
            		
            	
            }
            
            
            
            
            
            
            
            conn1.close();       
        }catch(SQLException e){
            System.out.println("Sa merde gros #1");
            e.printStackTrace();

        }
		
		
	}
	
	

    public static void main(String args[]) throws ParseException{
        
        Client monCli  = new Client(1,"Durant","Paul");
        monCli.info();
        
        
        //<DATE ESSAI 
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
        
        
        
        //lundi["8:00","17:00"]
        
        
        
        //System.out.println("Disponibilités pour le " + debut.format(timeFormatter));
        
        Veterinaire vet = new Veterinaire(1,"L","Belacqua");
        
        /*public Semaine(int id, String firstname, String lastname , Date lundi[] , Date mardi[] , 
    			Date mercredi[], Date jeudi[], Date vendredi[], Date samedi[])*/
        
        String[] lbLundi = {"15:00","19:00"};
        String[] lbMardi = {"8:00","12:00"};
        String[] lbMercredi = {"",""};
        String[] lbJeudi = {"8:00","12:00"};
        String[] lbVendredi = {"",""};
        String[] lbSamedi = {"8:00","12:00"};
        
        
        Semaine maSem = new Semaine(1,"L","Belacqua",lbLundi,lbMardi , lbMercredi, lbJeudi, lbVendredi, lbSamedi) ;
        
       // maSem.info();
        
        
        
        
        creationSemaine(1);
        System.out.println("Bienvenue sur Clinique Konsoru !");
        
        // chargement de la configuration de la persistence bonjour bonjour
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));
    }
    
}
