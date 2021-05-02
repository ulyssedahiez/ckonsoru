package com.fges.ckonsoru;


import java.sql.Connection;
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
import java.util.Date;
import java.util.Properties;


public class ConnexionBDD {

    public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();
	
    public String url = prop.getProperty("bdd.url");
    public String login = prop.getProperty("bdd.login");
    public String mdp = prop.getProperty("bdd.mdp");
    
    
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime debut = LocalDateTime.parse("05/05/2018 11:50", timeFormatter);
    
    // Afficher tout les rdv Client ! 
	
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






	// Recupere les diapo d'un client 

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
			return  listDispoJour;
	}



	// comparasion date Dispo et rdv pris se jour 
	public ArrayList<Object> comparaisonDate(LocalDateTime dateDispo ,ArrayList<Object> listDispoJour ){
		Connection conn1 = null;   
		ArrayList<Object> listRes = new ArrayList<Object>();	
		listRes.add(listDispoJour.get(0));
		try {
			conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);	
			String requeteDisJour = "SELECT * FROM rendezvous  LEFT JOIN veterinaire ON  rendezvous.vet_id = veterinaire.vet_id WHERE veterinaire.vet_nom= ? AND DATE(rv_debut) = ?";
			PreparedStatement prepStatDisJour = conn1.prepareStatement(requeteDisJour);
			prepStatDisJour.setString(1, listDispoJour.get(0).toString());

			prepStatDisJour.setDate(2, java.sql.Date.valueOf(dateDispo.toString().substring(0,10)));
	
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
							listIndex.add(i);			
						}
					}

					i++;
				}

				for (int j = listIndex.size()-1; j > 0; j--) {	
					listDispoJour.remove(listIndex.get(j).intValue());	
				}

			}		
			return listDispoJour;
		
		}catch(SQLException e) {
			 System.out.println("Connexion echoué #1");
				e.printStackTrace();
			
		}

		return  listDispoJour;
	}



	// Dispo all vet
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
	
		return  listReturn;
	}


	// Affichage pour tout les Veto
	public void AffichageDispoCorrect(ArrayList<Object> listAllVetDispo , LocalDateTime dateJour){
			
			String essai = dateJour.toString().substring(0,10);
			essai = essai.substring(8,10)  + "/"+  essai.substring(5,7) + "/" + essai.substring(0,4) ;
			
			ArrayList<String> st = new  ArrayList<String>();

		;

			for (Object monObLi : listAllVetDispo) {
				
				String maStOb = monObLi.toString();
				maStOb = maStOb.substring(1, maStOb.length()-1);
				String[] decoupe = maStOb.split(",");
				for (String stringfinal : decoupe) {
					st.add(stringfinal);
				}
			}

			ArrayList<String> noms = new  ArrayList<String>();
			ArrayList<Integer> indexNom = new  ArrayList<Integer>();
			ArrayList<String> dates =  new  ArrayList<String>();

			int i = 0;
			for (String res : st) {
				if(res != null){
					if(res.contains("1970-01-01")){
						dates.add(res.substring(12,res.length()-2));
						i++;
					}else{
						if(res != ""){
							noms.add(res);
							indexNom.add(i);
							i++;
						}
						
					}
				}
				
			}


			for (int j = 0; j < indexNom.size(); j++) {
				if(indexNom.get(j) == indexNom.get(indexNom.size()-1)){
					for (int l = indexNom.get(j).intValue()-1; l < dates.size(); l++){
					System.out.println(noms.get(indexNom.size()-1) + " : " + essai + " " + dates.get(l)

						);
					}
				}else{
					for (int k = 0; k < indexNom.get(j+1)-1; k++){
						System.out.println(noms.get(j) + " : " + essai + " " + dates.get(k)
						);						
					}
				}	
			}
	}



	// Prise de RDV 
	public void priseRdv(String dateRdv , String nomVet , String nomClient){
		Connection conn1 = null;

		try {
			String reform = dateRdv.substring(6,10) + "-" +  dateRdv.substring(0,2) + "-" + dateRdv.substring(3,5) + " "  + dateRdv.substring(11,16)+":00" ;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(reform);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			int idVet = 0;

			try{
				conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);

				String requeteVetId = "SELECT vet_id FROM veterinaire  where vet_nom =  ?";
				PreparedStatement prepStatVetId = conn1.prepareStatement(requeteVetId);
				prepStatVetId.setString(1, nomVet);

				
				ResultSet resultatVetId = prepStatVetId.executeQuery();
				while(resultatVetId.next()){

					idVet = resultatVetId.getInt("vet_id");
				}

				String requeteInsertRdv = "INSERT INTO rendezvous (vet_id,rv_debut,rv_client)"+
				"VALUES (?,?,?)";
				PreparedStatement pStmt = conn1.prepareStatement(requeteInsertRdv);
				pStmt.setInt(1,idVet);
				pStmt.setTimestamp(2, timestamp);
				pStmt.setString(3,nomClient);
				int rows =  pStmt.executeUpdate();
				if( rows > 0) {
					System.out.println("Un rendez-vous pour " + nomClient +  " avec " + nomVet + " a été réservé le " +  dateRdv);
					}

			}catch(SQLException e){
				System.out.println("Connexion echoué #1");
				e.printStackTrace();

			}

		} catch(Exception e) { //this generic but you can control another types of exception
			// look the origin of excption 
		}
		
	}

	// Suppr RDV 

	public void supprRdv(String dateRdv , String nomClient){
		Connection conn1 = null;

		try {
			String reform = dateRdv.substring(6,10) + "-" +  dateRdv.substring(0,2) + "-" + dateRdv.substring(3,5) + " "  + dateRdv.substring(11,16)+":00" ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(reform);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			
			try{
				conn1 = DriverManager.getConnection( this.url , this.login, this.mdp);


				String requeteDeleteRdv = "DELETE FROM rendezvous WHERE rv_debut = ? AND rv_client = ?";
				PreparedStatement pStmt = conn1.prepareStatement(requeteDeleteRdv);
				pStmt.setTimestamp(1, timestamp);
				pStmt.setString(2, nomClient);
				
				int rows =  pStmt.executeUpdate();
				if( rows > 0) {
					System.out.println("Un rendez-vous pour " + nomClient +  " pour le " +  dateRdv + " à été supprimé ");
					}

			}catch(SQLException e){
				System.out.println("Connexion echoué #1");
				e.printStackTrace();

			}


		} catch(Exception e) { 	
		}
	}
}




