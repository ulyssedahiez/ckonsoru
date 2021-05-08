package com.fges.ckonsoru.metier;


import java.time.LocalDateTime;

import com.fges.ckonsoru.bdd.DateConv;
import com.fges.ckonsoru.metier.Client;

public class RDV {
    private LocalDateTime dateRdv ;
    private String veto;
    private Client client;

    public  RDV(LocalDateTime dateRdv,String monVet,Client monClient){
        this.dateRdv = dateRdv;
        this.veto = monVet;
        this.client = monClient;
    }


    public LocalDateTime getdateRdv(){
        return this.dateRdv;
    }


    public String getVeto(){
        return this.veto;
    }

    public Client getClient(){
        return this.client;
    }

    @Override
    public String toString() {
        DateConv convertisseur = new DateConv();
        //return convertisseur.afficherGoodFormat(this.dateRdv.toString()) + "avec " + this.veto;
        return (this.dateRdv + "avec " + this.veto) ;
    }



}
