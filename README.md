#Projet  ckonsoru


Projet réalisé par Dahiez Ulysse et Burdy Simon


Lien du git : https://github.com/DahiezU/ckonsoru.git

## Partie XML réalisé par Dahiez Ulysse :

### AfficherRDV.java :

	- Cette Class sert à afficher les rendez-vous selon un client, nous avons qu'une seule fonction qui getAllRDV() elle va parcourir les clients selon leurs noms et les afficher s'il est identique à celui en paramètre. Comme dans la plupart des class, nous décomposons et recomposons des dates pour avoir le bon format affiché. Nous pouvons les retrouver garce au nom des noeuds.

### AjouterRDV.java :

	- Cette class sert à ajouter un rendez- une date donné, un client donné et un vétérinaire donnée. nous allons créer un nœud de rendez-vous et nous allons ajouter chacun de ces trois éléments dans des balises respective

### SupprimerRDV.java :

	- Cette class sert comme son nom l'indique à supprimer un rendez-vous selon la date et le nom du client, nous allons parcourir les nœuds de rendez vous et lorsque le nom et la date correspondent aux paramètres, nous allons supprimer le nœuds en question.

### Disponibilites.java :

	- Cette class a été la plus compliquée à mettre en place, au moment où nous avons commencés nous n'avions pas vu dans le sujet, nous l'avons donc conçu nous même, celle ci retourne toute les disponibilitées des vétérinaires d'une journée, dans un premier temps nous allons récupèrerer tout les crenneaux possible de la journée. en suite une fonction sert à lister les rendez-vous pris cette journée. Nous allons au finalement afficher la différence de ces deux listes.

### Ecran.java :

	- Cette class sert à afficher et à interagir entre l'utilisateur et les différentes classes.

### App.java :
	- Cette classe sert à lancer le programme.


## Partie BDD réalisé par Burdy Simon : 

  - Classe ConnexionBDD :
    Cette  classe   permet d'intéragir avec la BDD , c'est  la classe principale de l'application Ckonsoru . <br>
    Elle permet de  recuperer les informations de la bases et de les traiter \. <br>
    N'ayant ps vu que vous utilisiez la fonction creneauxDisponibles en sql , j'ai de nombreuses fonctions <br> 
    permettant d'avoir le meme résultat en bien moins propre et bien plus de lignes de code \.<br> 
        -  Function rdvClientAfficher : Affiche  tout les rdvs d'un Client <br> 
        -  Function  afficherDispo :recupere les diapo d'un client  <br> 
        -  Function comparaisonDate : fait les dispos d'un veto pour un jour  - les rdvs d'un veto pour 1 jour <br> 
        -  Function DispoAllVet :boucle la function comparaisonDate pour tout les veto  <br> 
        -  Function AffichageDispoCorrect : Affiche les dispo dans la console  <br> 
        -  Function priseRdv : Prend un rdv  <br> 
        -  Function supprRdv : supprime un rdv  <br> 
  -  Classe GestionAction : 
    Cette classe fait l'interface entre l'utilisateur et la classe connexionBDD , elle ecoute les actions <br>
    de l'utilisateur et execute les fonctions de la classe connexionBDD \. <br>
      -  Function selectCode :
          -   Boucle pour afficher les options 
      -  Function do Action :
          -  Execuite la bonne action 
