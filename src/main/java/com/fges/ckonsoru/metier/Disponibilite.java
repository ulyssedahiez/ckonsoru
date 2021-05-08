package com.fges.ckonsoru.metier;


import java.time.LocalDateTime;

public class Disponibilite {
    private LocalDateTime date;
    private String veto ;

    public Disponibilite( LocalDateTime date, String nonVet){
        this.date = date;
        this.veto = nonVet;
    }

    public  LocalDateTime getDate(){
        return this.date;
    }

    public String getVeto(){
        return this.veto;
    }

    @Override
    public String toString() {
        return  this.veto + " : " + this.date;
    }
  

}
