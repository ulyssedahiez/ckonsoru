package com.fges.ckonsoru;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
	public ArrayList<Object> afficherDispo(LocalDateTime dateDispo , int vet_id) {
		 Connection conn1 = null;   
			
			ArrayList<Object> listDispoJour = new ArrayList<Object>(); 
			
			String vet_nom = "";
			Timestamp heureDebut = null;
			Timestamp heureFin = null;
			DayOfWeek dayofweek = dateDispo.getDayOfWeek();
			
			try {
				conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);	
		        String requeteDisJour = "SELECT * FROM disponibilite  LEFT JOIN veterinaire ON  disponibilite.vet_id = veterinaire.vet_id WHERE disponibilite.vet_id = ? AND dis_jour = ?";;
		        PreparedStatement prepStatDisJour = conn1.prepareStatement(requeteDisJour);
		        prepStatDisJour.setInt(1, vet_id);
				prepStatDisJour.setInt(2, dayofweek.getValue());
		        ResultSet resultatDisJour = prepStatDisJour.executeQuery();
		        
		        while (resultatDisJour.next()) {
					 vet_nom = resultatDisJour.getString("vet_nom");
		        	 heureDebut = resultatDisJour.getTimestamp("dis_debut");
		        	 heureFin = resultatDisJour.getTimestamp("dis_fin");	
		       
		        	
		        	
		        	
		        }
		        listDispoJour.add(vet_nom);
				if(heureDebut != null){
					while(heureDebut.compareTo(heureFin) != 0) {
		        	
						long t=heureDebut.getTime();
						long m= 20*60*1000;
						listDispoJour.add(heureDebut);
	
						heureDebut = new Timestamp(t+m);
						
						
					}
				}else{
					return  listDispoJour;
				}
		        
		        
		        
				conn1.close();
				
			}catch(SQLException e) {
				 System.out.println("Connexion echoué #1");
		            e.printStackTrace();
				
			}
			//System.out.println(listDispoJour);
			return  listDispoJour;
	 }



	 public ArrayList<Object> comparaisonDate(LocalDateTime dateDispo ,ArrayList<Object> listDispoJour ){

		Connection conn1 = null;   
		ArrayList<Object> listRes = new ArrayList<Object>();
		
		listRes.add(listDispoJour.get(0));
		try {
			conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);	
			String requeteDisJour = "SELECT * FROM rendezvous  LEFT JOIN veterinaire ON  rendezvous.vet_id = veterinaire.vet_id WHERE veterinaire.vet_nom= ? AND DATE(rv_debut) = ?";;
			PreparedStatement prepStatDisJour = conn1.prepareStatement(requeteDisJour);
			prepStatDisJour.setString(1, listDispoJour.get(0).toString());

			prepStatDisJour.setDate(2, java.sql.Date.valueOf(dateDispo.toString().substring(0,10)));
			//java.sql.Date.valueOf(dateJour)
			ResultSet resultatDisJour = prepStatDisJour.executeQuery();
			
			
			while (resultatDisJour.next()) {
				Timestamp rvDebut = resultatDisJour.getTimestamp("rv_debut");
				listRes.add(rvDebut);	
				
			}	
			conn1.close();
			int i = 0;
			ArrayList<Integer> listIndex = new ArrayList<Integer>();
			if(listDispoJour.size() > 1){
				for (Object elementDispoJour : listDispoJour) {
					String dateEntiere = elementDispoJour.toString();
					String[] heures = dateEntiere.split(" ");
					
					for( Object elementRes : listRes){
						String dateRes = elementRes.toString();
						String[] heuresRes = dateRes.split(" ");
						if(heures[1].equals(heuresRes[1])){
							//System.out.println( "Mon heure 1 :" + heures[1] + " | Mon heure Res " + heuresRes[1]);
							listIndex.add(i);
								
						}
				}
	
					i++;
				}

				for (int j = listIndex.size()-1; j > 0; j--) {
					//System.out.println(listIndex.get(j));
					listDispoJour.remove(listIndex.get(j).intValue());	
				}


			}
			

		
	

			//System.out.println(listDispoJour);
			return listDispoJour;
		
			
		}catch(SQLException e) {
			 System.out.println("Connexion echoué #1");
				e.printStackTrace();
			
		}

		return  listDispoJour;
	}


	public ArrayList<Object> dispoAllVet(LocalDateTime dateDispo) {
		Connection conn1 = null;
		ArrayList<Integer> listId = new ArrayList<Integer>();
		ArrayList<Object> listReturn =  new ArrayList<Object>();
		try {
			conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);	
			String requeteDisJour = "SELECT * FROM veterinaire";
			Statement statement = conn1.createStatement();
            ResultSet resultat = statement.executeQuery(requeteDisJour);

			while (resultat.next()) {
				int vet_id = resultat.getInt("vet_id");
				listId.add(vet_id);
			}
			conn1.close();
			
			for (Integer id : listId) {
				listReturn.add(this.comparaisonDate(dateDispo, this.afficherDispo(dateDispo, id)));
				
			}

			
		}catch(SQLException e) {
			 System.out.println("Connexion echoué #1");
				e.printStackTrace();
			
		}
		//System.out.println("La grande list est : " +listReturn);
		return  listReturn;
	}




	/*public void AffichageDispoCorrect(ArrayList<Object> listAllVetDispo){
		for (Object list : listAllVetDispo) {
			//System.out.println("ma list est :" + list);
			//ObjectGraphMeasurer.measure(list)
			//String[] stringValues = (String[])list[0];
		
			
			
		}
	}*/
	 
}




