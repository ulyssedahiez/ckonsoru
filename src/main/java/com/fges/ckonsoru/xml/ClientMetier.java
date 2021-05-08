package com.fges.ckonsoru.xml;

import java.util.Set;
import java.io.Serializable;

// La classe doit être sérialisable
public class ClientMetier implements Serializable {
   
   // les propriétés sont privées (interfaces à privilégier)
   private String name;
   private ArrayList<RendezVousMetier> rdvs;

   // il existe un constructeur public sans argument
   public ClientMetier() {
   }


   // getters
   public String getName() { return name; }
   public Set<ClientMetier> getFriends() { return rdvs; }

   // setters
   public void setName(String name) { this.name = name; }
   public void setFriends(Set<ClientMetier> friends) { this.rdvs = friends; }

}