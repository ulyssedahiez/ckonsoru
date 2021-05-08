package com.fges.ckonsoru.bdd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fges.ckonsoru.ConfigLoader;
import com.fges.ckonsoru.metier.Disponibilite;
import com.fges.ckonsoru.metier.PlanningJour;
import com.fges.ckonsoru.metier.RDV;
import com.fges.ckonsoru.metier.Client;

public class ConnexionBddDAO {
    protected Connection bddConn;

    public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();

    private String url = prop.getProperty("bdd.url");
    private String login = prop.getProperty("bdd.login");
    private String mdp = prop.getProperty("bdd.mdp");
    
    //protected Connection bddConn = DriverManager.getConnection(this.url , this.login, this.mdp);

    public Connection connexionGestionRdv() throws SQLException{
        return this.bddConn = DriverManager.getConnection( this.url , this.login, this.mdp);
    }


    public void ListerPlanningParVeto() throws SQLException{

        String sqlPlanning= "  SELECT vet_nom, jou_libelle, dis_debut, dis_fin \n"
        +  "FROM disponibilite \n"
        + "INNER JOIN veterinaire \n" 
        +"ON veterinaire.vet_id = disponibilite.vet_id \n" 
        + " INNER JOIN jour \n" 
        +"ON jour.jou_id = dis_jour \n" 
        +" ORDER BY vet_nom, dis_id\n" ;
        
        PreparedStatement monPrepStatLen = this.connexionGestionRdv().prepareStatement(sqlPlanning);
        ResultSet resultatLen = monPrepStatLen.executeQuery();
        
        DateConv convDate = new DateConv();
        List<Disponibilite> mesDispo = new ArrayList<Disponibilite>();
        
        while (resultatLen.next()) {
            
            String nomVet = resultatLen.getString("vet_nom");
            String jour =  resultatLen.getString("jou_libelle");
            LocalDateTime heure_debut = resultatLen.getTimestamp("dis_debut").toLocalDateTime();
            LocalDateTime heure_fin = resultatLen.getTimestamp("dis_fin").toLocalDateTime();
            Disponibilite maDispo = new Disponibilite(heure_debut , nomVet);
            mesDispo.add(maDispo);
         
            while(heure_debut.equals(heure_fin) == false){
                heure_debut = convDate.ajout20Minute(heure_debut);
                maDispo = new Disponibilite(heure_debut, nomVet);
                mesDispo.add(maDispo);
            }

            PlanningJour planning = new PlanningJour(jour, nomVet, mesDispo);

            System.out.println(planning.toString());

        }  
    }


    public List<Disponibilite> ListerDisponibilite(LocalDateTime dateDonnee){
    List<Disponibilite> mesDispos = new ArrayList<Disponibilite>();
    return mesDispos;
    }


    public List<RDV> ListerRDV(Client monClient) throws SQLException{
        List<RDV> mesRdv = new ArrayList<RDV>();
        
        String sqlRdv= "SELECT rv_id, rv_debut, rv_client , vet_nom \n"
        +"FROM rendezvous \n"
        +"FULL JOIN veterinaire \n"
        +"ON rendezvous.vet_id = veterinaire.vet_id \n"
        +"WHERE rv_client = ? \n"
        +"ORDER BY rv_debut DESC \n" ;

        PreparedStatement monPrepStatLen = this.connexionGestionRdv().prepareStatement(sqlRdv);
        monPrepStatLen.setString(1, monClient.getNom());

        ResultSet resultatLen = monPrepStatLen.executeQuery();
        
        while (resultatLen.next()) {
            LocalDateTime dateRdv = resultatLen.getTimestamp("rv_debut").toLocalDateTime();
            String nomVet =  resultatLen.getString("vet_nom");

            RDV monRdv  = new RDV(dateRdv, nomVet, monClient.getNom());
            
            System.out.println(monRdv);
            
            mesRdv.add(monRdv);
        }
        return mesRdv;
        
        
    }


    /*public void SupprimerRDV(LocalDateTime dateDonnee , Client monClient){
      
		String requeteDeleteRdv = "DELETE FROM rendezvous WHERE rv_debut = ? AND rv_client = ?";
		PreparedStatement pStmt = this.connexionGestionRdv().prepareStatement(requeteDeleteRdv);
		pStmt.setTimestamp(1, dateDonnee);
		pStmt.setString(2, monClient.getNom());
    }*/


    public void AjoutRDV(LocalDateTime dateDonnee , Client monClient , String nomVet) throws SQLException{
        String requeteInsertRdv = "INSERT INTO rendezvous (vet_id, rv_debut, rv_client) \n"
        +"VALUES((SELECT vet_id FROM veterinaire WHERE vet_nom = ?), \n"
            +" ?,\n"
            +" ? )";
		
        
        Timestamp timestamp = Timestamp.valueOf(dateDonnee);

        PreparedStatement pStmt = this.connexionGestionRdv().prepareStatement(requeteInsertRdv);
        pStmt.setString(1,nomVet);
        pStmt.setTimestamp(2, timestamp);
        pStmt.setString(3,monClient.getNom());
       
    }
    





        
    

}
