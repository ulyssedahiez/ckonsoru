package com.fges.ckonsoru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Client {
    public int id;
    public String nom;
    public String prenom;

    public Client(String name ,String firstname ){
        this.nom = name;
        this.prenom = firstname;
        
    }


    public void info(){
        System.out.println( "Nom : "+ this.nom + " ,Prenom :" + this.prenom);
    }
    /*bdd.url=jdbc:postgresql://localhost/ckonsoru
    bdd.login=postgres
    bdd.mdp=$im1789Jps*/



    public void showVet(){
       
        Connection conn1 = null;
        String dbURL1 = "jdbc:postgresql://localhost:5432/ckonsoru";
        String username = "postgre";
        String password = "$im1789Jps";
        try{
            conn1 = DriverManager.getConnection(dbURL1 , username,password);
            System.out.println("Connected to database #1");
            conn1.close();
        }catch(SQLException e){
            System.out.println("Sa merde gros #1");
            e.printStackTrace();

        }
        
        
                
           
              
           
        

    }







}
