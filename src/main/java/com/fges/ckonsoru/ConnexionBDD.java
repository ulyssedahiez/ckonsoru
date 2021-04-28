package com.fges.ckonsoru;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

public class ConnexionBDD {
    public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();
	
    public String url = prop.getProperty("bdd.url");
    public String login = prop.getProperty("bdd.login");
    public String mdp = prop.getProperty("bdd.mdp");
    
    
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
    
    

	
	public void lesdispo(String dateJour) {
		Connection conn1 = null;        
        /*DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
        System.out.println(debut.getDayOfWeek().getValue());*/
		
		
		String str = dateJour+" 11:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            
		
		
		
		try{
            conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);
            System.out.println("Connected to database #1");   								
            
           
        
            String sql = "SELECT * FROM disponibilite INNER JOIN rendezvous ON disponibilite.vet_id = rendezvous.vet_id INNER JOIN veterinaire ON disponibilite.vet_id = veterinaire.vet_id WHERE DATE(rv_debut) = ?";
            PreparedStatement monPrepStat = conn1.prepareStatement(sql);
            monPrepStat.setDate(1, java.sql.Date.valueOf(dateJour));
            ResultSet resultat = monPrepStat.executeQuery();
            
            
  
            if(!resultat.isBeforeFirst()) {
            	//System.out.println(dateTime.getDayOfWeek().getValue());
            	String dispoJour = "SELECT * FROM disponibilite LEFT JOIN veterinaire ON disponibilite.vet_id = veterinaire.vet_id WHERE dis_jour = ?";
            	PreparedStatement monPrepStatDispoJour = conn1.prepareStatement(dispoJour);
            	monPrepStatDispoJour.setInt(1, dateTime.getDayOfWeek().getValue());
                ResultSet resultatDispoJour = monPrepStatDispoJour.executeQuery();
                
                //System.out.println(resultatDispoJour);
                
                
                
                
                while (resultatDispoJour.next()) {
                	String nomVetDj = resultatDispoJour.getString("vet_nom");
                	Timestamp heureDebutDj = resultatDispoJour.getTimestamp("dis_debut");
                	Timestamp heureFinDj = resultatDispoJour.getTimestamp("dis_fin");
                	System.out.println(" Nom vet ->" + nomVetDj  + " : " +
	            	" De HeureDebut  :" +  heureDebutDj  + " à  "  +  heureFinDj  + "\n" );
	            }
            	
                conn1.close(); 
            }else{
            
	            while (resultat.next()) {
	            	String nomVet = resultat.getString("vet_nom");
	            	Timestamp heureDebut = resultat.getTimestamp("dis_debut");
	            	Timestamp heureFin = resultat.getTimestamp("dis_fin");
	            	Timestamp rv_debut = resultat.getTimestamp("rv_debut");
	            	System.out.println(" Nom vet ->" + nomVet  + " : " +
	            	" De HeureDebut  :" +  heureDebut  + " à  "  +  heureFin  + "\n" +
	            			"rdv debut :" + rv_debut );	
	            }
            }
	              
	        conn1.close();       
        }catch(SQLException e){
            System.out.println("Connexion echoué #1");
            e.printStackTrace();

        }

		
		
	}
	
	
	

	public void rdvDispo(String dateJour) {
		Connection conn1 = null;   
		String str = dateJour+" 11:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		int nbJourSemaine = dateTime.getDayOfWeek().getValue();
		
		String[] heureDebutTab; 
		String[] heureFinTab; 
		
		try {
			conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);	
	        String requeteDisJour = "SELECT * FROM disponibilite LEFT JOIN veterinaire ON disponibilite.vet_id = veterinaire.vet_id WHERE dis_jour = ?";
	        PreparedStatement prepStatDisJour = conn1.prepareStatement(requeteDisJour);
	        prepStatDisJour.setInt(1,nbJourSemaine);
	        ResultSet resultatDisJour = prepStatDisJour.executeQuery();
	        
	        while (resultatDisJour.next()) {
	        	String nomVet = resultatDisJour.getString("vet_nom");
	        	Timestamp heureDebut = resultatDisJour.getTimestamp("dis_debut");
	        	Timestamp heureFin = resultatDisJour.getTimestamp("dis_fin");	
	       
	        	System.out.println(nomVet + " : " + heureDebut.toString().substring(11,16) + " to "
	        	+ heureFin.toString().substring(11,16) );
	        }
			
			conn1.close();
		}catch(SQLException e) {
			 System.out.println("Connexion echoué #1");
	            e.printStackTrace();
			
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public  void rdvClientAfficher(String nomClient) {
		Connection conn1 = null;  
		try{
            conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);
          
            String sqlLen = "SELECT COUNT(*) FROM rendezvous LEFT JOIN veterinaire ON rendezvous.vet_id = veterinaire.vet_id  WHERE rv_client = ?";
            PreparedStatement monPrepStatLen = conn1.prepareStatement(sqlLen);
            monPrepStatLen.setString(1, nomClient);
            ResultSet resultatLen = monPrepStatLen.executeQuery();
            while (resultatLen.next()) {
            	int count = resultatLen.getInt("count");
            	System.out.println(count + " rendez-vous trouvé(s) pour " + nomClient );
            	
            }
            
            
            String sql = "SELECT * FROM rendezvous LEFT JOIN veterinaire ON rendezvous.vet_id = veterinaire.vet_id  WHERE rv_client = ?";
            PreparedStatement monPrepStat = conn1.prepareStatement(sql);
            monPrepStat.setString(1, nomClient);
            ResultSet resultat = monPrepStat.executeQuery();
            
  

	        while (resultat.next()) {
	            String nomVet = resultat.getString("vet_nom");
            	Timestamp heureRvDebut = resultat.getTimestamp("rv_debut");
            	System.out.println(heureRvDebut + " avec "  + nomVet);	
            }
        
	              
	        	conn1.close();       
        }catch(SQLException e){
            System.out.println("Connexion echoué #1");
            e.printStackTrace();

        }
	}
	 @SuppressWarnings("deprecation")
	public void afficherDispo(LocalDateTime dateDispo) {
		 Connection conn1 = null;   
			
			ArrayList listDispoJour = new ArrayList(); 
			
			Timestamp heureDebut = null;
			Timestamp heureFin = null;
			
			try {
				conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);	
		        String requeteDisJour = "SELECT * FROM disponibilite WHERE disponibilite.vet_id = ?";;
		        PreparedStatement prepStatDisJour = conn1.prepareStatement(requeteDisJour);
		        prepStatDisJour.setInt(1, 1);
		        ResultSet resultatDisJour = prepStatDisJour.executeQuery();
		        
		        while (resultatDisJour.next()) {
		        	
		        	 heureDebut = resultatDisJour.getTimestamp("dis_debut");
		        	 heureFin = resultatDisJour.getTimestamp("dis_fin");	
		       
		        	
		        	
		        	System.out.println(heureDebut);
		        	
		        	
		        }
		        
		        while(heureDebut.compareTo(heureFin) != 0) {
		        	
		        	long t=heureDebut.getTime();
		        	long m= 20*60*1000;
		        	listDispoJour.add(heureDebut);

		        	heureDebut = new Timestamp(t+m);
		        	
		        	
		        }
		        
		        
		        System.out.println(listDispoJour);
				
				conn1.close();
			}catch(SQLException e) {
				 System.out.println("Connexion echoué #1");
		            e.printStackTrace();
				
			}
	 }
	 
}




