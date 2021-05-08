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

public class ConnexionBddDAO {
    protected Connection bddConn;

    public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();

    private String url = prop.getProperty("bdd.url");
    private String login = prop.getProperty("bdd.login");
    private String mdp = prop.getProperty("bdd.mdp");
    
    //protected Connection bddConn = DriverManager.getConnection(this.url , this.login, this.mdp);

    public Connection connexionGestionRdv() throws SQLException{
        /*this.url = url ;
        this.login = login;
        this.mdp = mdp;*/
        return this.bddConn = DriverManager.getConnection( this.url , this.login, this.mdp);
    }


    public void ListerPlanningParVeto() throws SQLException{
        //List<Disponibilite> mesPlannings = new ArrayList<Disponibilite>();

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
            //System.out.println(convDate.ajout20Minute(heure_debut.toLocalDateTime()));
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


    public List<RDV> ListerRDV(Client monClient){
        List<RDV> mesRdv = new ArrayList<RDV>();
        return mesRdv;
    }


    /*public void SupprimerRDV(LocalDateTime dateDonnee , Client nomClient){
        
    }*/


    /* public void AjoutRDV(LocalDateTime dateDonnee , Client nomClient ){
        
    }
    
    */




        
    

}
