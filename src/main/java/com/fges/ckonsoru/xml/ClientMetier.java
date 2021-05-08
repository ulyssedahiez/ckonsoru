package com.fges.ckonsoru.xml;

import java.util.Set;
import java.io.Serializable;

// La classe doit être sérialisable
public class ClientMetier implements Serializable {

   private static final long serialVersionUID = 1L;
   
   // les propriétés sont privées (interfaces à privilégier)
   private String name;
   private Set<ClientMetier> rdv;

   // il existe un constructeur public sans argument
   public ClientMetier() {
   }


   // getters
   public String getName() { return name; }
   public Set<ClientMetier> getFriends() { return rdv; }

   // setters
   public void setName(String name) { this.name = name; }
   public void setFriends(Set<ClientMetier> friends) { this.rdv = friends; }

}