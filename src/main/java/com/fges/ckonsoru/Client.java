package com.fges.ckonsoru;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
<<<<<<< HEAD
=======
import java.sql.PreparedStatement;
>>>>>>> main
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class Client {
    public int id;
    public String nom;
    public String prenom;
    
    public ConfigLoader maConf = new ConfigLoader();
    public Properties prop = maConf.getProperties();
	
    public String url = prop.getProperty("bdd.url");
    public String login = prop.getProperty("bdd.login");
    public String mdp = prop.getProperty("bdd.mdp");
    
    

    public Client(int id ,String name ,String firstname ){
    	this.id =  id;
        this.nom = name;
        this.prenom = firstname;
        
    }


    public void info(){
        System.out.println( "Nom : "+ this.nom + " ,Prenom :" + this.prenom);
    }



<<<<<<< HEAD
    public void showVet(){
       
        Connection conn1 = null;
        String dbURL1 = "jdbc:postgresql://localhost:5432/ckonsoru";
        String username = "postgres";
        String password = "Bon007";
        try{
            conn1 = DriverManager.getConnection(dbURL1 , username,password);
            System.out.println("Connected to database #1");
            
            ResultSet resultats = null;
            String requete = "SELECT * FROM veterinaire";

            
            Statement stmt = conn1.createStatement();
            resultats = stmt.executeQuery(requete);
            
            
            while(resultats.next()) {
            	int id = resultats.getInt("vet_id");
            	String name = resultats.getString("vet_nom");
            	
            	System.out.println("id : " + id + " & " +"name : " + name);
            	
            }
            String sql = "Insert INTO  ()";
            
            
            conn1.close();
            
=======

    /*public void showVet(){
        Connection conn1 = null;        
        try{
        	
            conn1 = DriverManager.getConnection(url , login,mdp);
            System.out.println("Connected to database #1");    
            conn1.close();       
>>>>>>> main
        }catch(SQLException e){
            System.out.println("Sa merde gros #1");
            e.printStackTrace();

        }
<<<<<<< HEAD
        
       
        
        
        
                
           
              
           
        

    }
=======
    }*/
>>>>>>> main







}
