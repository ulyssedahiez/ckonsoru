package com.fges.ckonsoru.bdd;


import java.time.LocalDateTime;

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



}
