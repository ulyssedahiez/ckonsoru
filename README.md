# ckonsoru


#Projet réalisé par Dahiez Ulysse et Burdy Simon


Lien du git : https://github.com/DahiezU/ckonsoru.git

# Partie XML réalisé par Dahiez Ulysse :

  -


# Partie BDD réalisé par Burdy Simon : 

  - Classe ConnexionBDD :
    Cette  classe   permet d'intéragir avec la BDD , c'est  la classe principale de l'application Ckonsoru . <br>
    Elle permet de  recuperer les informations de la bases et de les traiter \. <br>
    N'ayant ps vu que vous utilisiez la fonction creneauxDisponibles en sql , j'ai de nombreuses fonctions <br> 
    permettant d'avoir le meme résultat en bien moins propre et bien plus de ligne de code \.<br> 
        -  Function rdvClientAfficher : <br> 
            -   Affiche  tout les rdvs d'un Client <br> 
        -   Function  afficherDispo : <br> 
            -   recupere les diapo d'un client  <br> 
        -  Function comparaisonDate : <br> 
            -   fait les dispos d'un veto pour un jour  - les rdvs d'un veto pour 1 jour <br> 
        - Function DispoAllVet : <br> 
            -  boucle la function comparaisonDate pour tout les veto  <br> 
        -  Function AffichageDispoCorrect : <br> 
            -  Affiche les dispo dans la console  <br> 
        -  Function priseRdv : <br> 
            -  Prend un rdv  <br> 
         - Function supprRdv : <br> 
           -  supprime un rdv  <br> 
  -  Classe GestionAction : 
    Cette classe fait l'interface entre l'utilisateur et la classe connexionBDD , elle ecoute les actions <br>
    de l'utilisateur et execute les fonctions de la classe connexionBDD \. <br>
      -  Function selectCode :
          -   Boucle pour afficher les options 
      -  Function do Action :
          -  Execuite la bonne action 
