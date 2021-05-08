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


    /**public void ListerPlanningParVeto(String nomVeto , String jourSemaine ) throws SQLException{

        String sqlPlanning= "  SELECT vet_nom, jou_libelle, dis_debut, dis_fin \n"
        +  "FROM disponibilite \n"
        + "INNER JOIN veterinaire \n" 
        +"ON veterinaire.vet_id = disponibilite.vet_id \n" 
        + " INNER JOIN jour \n" 
        +"ON jour.jou_id = dis_jour \n"
        +"WHERE vet_nom = ? AND\n" 
        +"WHERE jou_libelle = ?\n "
        +" ORDER BY vet_nom, dis_id\n" ;
        
        PreparedStatement monPrepStatLen = this.connexionGestionRdv().prepareStatement(sqlPlanning);
        monPrepStatLen.setString(1, nomVeto);
        monPrepStatLen.setString(2, jourSemaine);
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
     **/


    public List<Disponibilite> ListerDisponibilite(LocalDateTime dateDonnee) throws SQLException{
        List<Disponibilite> mesDispos = new ArrayList<Disponibilite>();
        
        String sqlPlanning = "WITH creneauxDisponibles AS \n"
        +"(SELECT vet_nom, generate_series( ? ::date+dis_debut, \n"
                               +" ? ::date+dis_fin-'00:20:00'::time, \n"
                               +"'20 minutes'::interval) debut\n"
        +"FROM disponibilite \n"
            +"INNER JOIN veterinaire \n"
               +" ON veterinaire.vet_id = disponibilite.vet_id \n"
        +"WHERE dis_jour = EXTRACT('DOW' FROM ? ::date) \n"
        +"ORDER BY vet_nom, dis_id), \n"
        +"creneauxReserves AS  \n"
        +"(SELECT vet_nom, rv_debut debut \n"
        +" FROM rendezvous \n"
            +"INNER JOIN veterinaire \n"
            +"ON veterinaire.vet_id = rendezvous.vet_id \n"
            +"WHERE rv_debut \n"
            +"BETWEEN ? ::date \n" 
            +"AND ? ::date +'23:59:59'::time), \n"
        +"creneauxRestants AS \n"
        +"(SELECT * FROM creneauxDisponibles \n"
        +"EXCEPT \n"
        +"SELECT * FROM creneauxReserves) \n"
        +"SELECT * FROM creneauxRestants \n"
        +"ORDER BY vet_nom, debut \n" ;
        
      
        PreparedStatement monPrepStatLen = this.connexionGestionRdv().prepareStatement(sqlPlanning);
        


        String usdate = dateDonnee.toString().substring(0,10);
        String[] arr = usdate.split("-");
        String eudate = arr[2] + "-" + arr[1] + "-" + arr[0];
     
        
        
        monPrepStatLen.setString(1, eudate);
        monPrepStatLen.setString(2, eudate);
        monPrepStatLen.setString(3, eudate);
        monPrepStatLen.setString(4, eudate);
        monPrepStatLen.setString(5, eudate);

      
        ResultSet resultatLen = monPrepStatLen.executeQuery();
     
        while (resultatLen.next()) {
            LocalDateTime date  = resultatLen.getTimestamp("debut").toLocalDateTime();
       
            String nonVet = resultatLen.getString("vet_nom");
           
            Disponibilite creneauDispo = new Disponibilite(date ,nonVet );
           mesDispos.add(creneauDispo);
            
        }


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

            RDV monRdv  = new RDV(dateRdv, nomVet, monClient);
            
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
