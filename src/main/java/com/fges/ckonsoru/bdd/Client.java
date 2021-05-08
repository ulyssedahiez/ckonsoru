package com.fges.ckonsoru.bdd;

import java.util.List;

public class Client{
    private String nom;
    private List<RDV> rdvs ;

    public  Client( String nom,List<RDV> rdvs){
        this.nom = nom;
        this.rdvs = rdvs;
    }

    public String getNom(){
        return this.nom;
    }


    public List<RDV> getRdvs(){
        return 
        this.rdvs;
    }



}
