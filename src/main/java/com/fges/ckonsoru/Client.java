package com.fges.ckonsoru;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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




    /*public void showVet(){
        Connection conn1 = null;        
        try{
        	
            conn1 = DriverManager.getConnection(url , login,mdp);
            System.out.println("Connected to database #1");    
            conn1.close();       
        }catch(SQLException e){
            System.out.println("Sa merde gros #1");
            e.printStackTrace();

        }
    }*/







}
