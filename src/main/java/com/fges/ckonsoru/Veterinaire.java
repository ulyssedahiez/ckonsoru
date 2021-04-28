package com.fges.ckonsoru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;

public class Veterinaire {
	public int id;
	public String nom;
	public String prenom;
	
	
	
	
	
    public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();
	
    public String url = prop.getProperty("bdd.url");
    public String login = prop.getProperty("bdd.login");
    public String mdp = prop.getProperty("bdd.mdp");
    
	
	
	
	public Veterinaire(int id ,String firstname , String lastname) {
		this.id = id ;
		this.nom = lastname;
		this.prenom = firstname;
	}
	
	
	
	public Semaine creationSemaine(){
		//Timestamp timestamp = Timestamp.valueOf("10:10:10");
        //System.out.println(timestamp);
		Connection conn1 = null;        
        try{
        	
            conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);
            System.out.println("Connected to database #1");   
            
            String sql = "SELECT * FROM disponibilite where vet_"
            		+ "id="+id_vet+"";
            
            Statement statement = conn1.createStatement();
  
            ResultSet resultat = statement.executeQuery(sql);
            while (resultat.next()) {
            	int vid = resultat.getInt("vet_id");
            	int jour = resultat.getInt("dis_jour");
            	Timestamp heureDebut = resultat.getTimestamp("dis_debut");
            	Timestamp heureFin = resultat.getTimestamp("dis_fin");
            	System.out.println(" ID du vet  ->" + vid + "\n"  + 
            	" Numero jour ->  " +  jour  + "\n"  + 
            	" Debut -> " +  heureDebut  + "\n"  + 
            	" Fin -> " +  heureFin + "\n");	
            }
              
            conn1.close();       
        }catch(SQLException e){
            System.out.println("Sa merde gros #1");
            e.printStackTrace();

        }
		
		
	}
	
	
	

}
