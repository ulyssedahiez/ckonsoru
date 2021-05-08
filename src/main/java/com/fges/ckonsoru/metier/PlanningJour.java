package com.fges.ckonsoru.metier;

import java.util.List;

public class PlanningJour{
    private  String jour;
    private  List<Disponibilite>  dispos;
    private  String veto ; 

    public PlanningJour(String jour , String veto , List<Disponibilite> dispos){
        this.jour = jour ;
        this.dispos = dispos ;
        this.veto = veto;
    }

    public  List<Disponibilite> getDispos(){
        return this.dispos ;
    }


    @Override
    public String toString() {
        return "Veto : " + this.veto + " , le jour :  " + this.jour + " , mes dispo " + this.dispos +" \n";
    }
  


 
    
}
