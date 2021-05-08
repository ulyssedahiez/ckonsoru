package com.fges.ckonsoru.bdd;

import java.time.LocalDateTime;
import java.util.List;

public interface GestionRdvDAO {

   
    public List<Disponibilite> ListerPlanningParVeto();
    public List<Disponibilite> ListerDisponibilite(LocalDateTime dateDonnee);
    public List<RDV> ListerRDV(Client monClient);
    public void SupprimerRDV(LocalDateTime dateDonnee , Client nomClient);
    public void AjoutRDV(LocalDateTime dateDonnee , Client nomClient );


    /*public List<Disponibilite> AfficherListePlanningParVeto(){
     
      
         
    }*/
    
}
