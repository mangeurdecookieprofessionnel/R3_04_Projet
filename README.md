
Maxime COURDAVAULT

208

Tristan PIOTON

208

Moussa WAGUE

207

Jean-François DENA

208



PROJET R3.04 : Projet Jeu d’échec



Présentation globale

Projet

Le projet consiste à développer et tester un programme permettant  à deux joueurs de jouer une finale de type KRK aux échecs, c’est à dire une fin de partie dans laquelle les blancs ont un roi (King) et une tour (Rook) et les noirs un roi.

Le projet porte sur les principes SOLID, la Clean architecture, la programmation dirigée par les tests, le travail en équipe, et la gestion des versions avec git. Le GUI (Graphical User Interface) est fourni par un outil externe : CuteChess, via le protocole UCI.

Membres

Voici les membres de notre équipe, ainsi que leurs groupes :

Maxime COURDAVAULT

208

Tristan PIOTON

208

Moussa WAGUE

207

Jean-François DENA

208

Mode d’emploi

MODE D’EMPLOI DE L’APPLICATION R3_04 – MOTEUR D’ÉCHECS UCI

PRÉREQUIS



Avant d’utiliser l’application, il est nécessaire d’avoir installé : CuteChess, Java, l’archive ZIP du projet R3_04.

Décompresser l’archive dans un dossier de votre choix.

Localiser java.exe en entrant commande suivante dans l’invite de commandes Windows : where java

Repérer le chemin vers le fichier java.exe (exemple : C:\Users...\jdks\openjdk-24\bin\java.exe)

Copier coller le fichier java.exe dans le dossier suivant du projet : R3_04_Projet-main\src\cutechess



CONFIGURATION DE CUTECHESS



Ouvrir CuteChess.

Aller dans le menu : Tools → Settings → Engines

Cliquer sur le bouton “+” en bas à gauche.

Renseigner le chemin du fichier “monmoteur.bat” situé dans le dossier : R3_04_Projet-main\src\cutechess

Cliquer sur OK.

Fermer complètement CuteChess puis le rouvrir.



LANCEMENT D’UNE PARTIE

Dans CuteChess, créer une nouvelle partie.

Sélectionner le moteur ajouté précédemment comme CPU.

Entrer la position initiale suivante (FEN) :

4k3/6R1/8/8/2K5/8/8/8 w - - 0 1

Valider avec OK.

Profiter de la partie.



Présentation technique

Ce paragraphe est verrouillé par Maxime COURDAVAULT
Clean architecture



Diagramme d'architecture

GROS CACA

SOLID

Design Patterns (s'il y en a)

Tests unitaires



Dans le cadre de ce projet nous avons réalisé des tests unitaires afin de vérifier les méthodes importantes une par une et maintenir le code plus facilement. Nous avons principalement testé 3 classes : King, Rook et Board.

Pour King nous avons vérifier les cases dans lesquels il peut aller en fonction de sa position : il n’aura pas les mêmes destinations possibles si il est au centre, sur le côté ou dans un coin.

public class TestKing {

@Test
// Test des possibilités dans une coordonnée quelconque
public void testmid(){
// Création du roi aux coordonnées [5;5]
IPiece roi = new King(Couleur.WHITE);
Square squareKing = new Square(5,5);

       // Liste des mouvements possibles 
       Set<Square> list = new HashSet<>(); 
       list.add(new Square(4,4)); 
       list.add(new Square(4,5)); 
       list.add(new Square(4,6)); 
       list.add(new Square(5,4)); 
       list.add(new Square(5,6)); 
       list.add(new Square(6,4)); 
       list.add(new Square(6,5)); 
       list.add(new Square(6,6)); 
        
      // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvements = roi.aplatir(roi.mouvement(squareKing)); 
 
       // Comparaison des deux listes 
       assertEquals(list,mouvements); 
}

@Test
// Test des possibilités sur le côté
public void testside(){
// Création du roi aux coordonnées [0;5]
IPiece roi = new King(Couleur.WHITE);
Square squareKing = new Square(0,5);

       // Liste des mouvements possibles 
       Set<Square> list = new HashSet<>(); 
       list.add(new Square(1,5)); 
       list.add(new Square(0,6)); 
       list.add(new Square(1,4)); 
       list.add(new Square(0,4)); 
       list.add(new Square(1,6)); 
 
       // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvements = roi.aplatir(roi.mouvement(squareKing)); 
 
       // Comparaison des deux listes 
       assertEquals(list,mouvements); 
}

@Test
// Test des possibilités en corner
public void testcorner(){
// Création du roi aux coordonnées [7;7]
IPiece roi = new King(Couleur.WHITE);
Square squareKing = new Square(7,7);

      // Liste des mouvements possibles 
       Set<Square> list = new HashSet<>(); 
       list.add(new Square(6,6)); 
       list.add(new Square(6,7)); 
       list.add(new Square(7,6)); 
        
      // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvements = roi.aplatir(roi.mouvement(squareKing)); 
 
       // Comparaison des deux listes 
       assertEquals(list,mouvements); 
}
}



En revanche pour Rook, peut importe sa position, il aura toujours le même nombre de cases possibles, nous avons donc réaliser un unique test :

public class TestRook {

@Test
public void test(){
// Création de la tour aux coordonnées [5;5]
IPiece tour = new Rook(Couleur.WHITE);
Square squareRook = new Square(5,5);

      // Liste des mouvements possibles 
       Set<Square> list = new HashSet<>(); 
       list.add(new Square(5,6)); 
       list.add(new Square(5,4)); 
       list.add(new Square(6,5)); 
       list.add(new Square(4,5)); 
       list.add(new Square(5,7)); 
       list.add(new Square(5,3)); 
       list.add(new Square(7,5)); 
       list.add(new Square(3,5)); 
       list.add(new Square(5,2)); 
       list.add(new Square(2,5)); 
       list.add(new Square(5,1)); 
       list.add(new Square(1,5)); 
       list.add(new Square(5,0)); 
       list.add(new Square(0,5)); 
        
      // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvements = tour.aplatir(tour.mouvement(squareRook)); 
 
       // Comparaison des deux listes 
       assertEquals(list,mouvements); 
}
}



Enfin, la classe Board est celle qui contient le plus de tests. En effet nous avons du dans un premier temps vérifier les déplacements possibles des pièces en prenant en compte les pièces autours :

public class TestBoard {
// Création du plateau
Board board = new Board();
@Test
public void testPositionKingW(){
// Liste des mouvements possibles pour le roi blanc
Set<Square> listRoiB = new HashSet<>();
listRoiB.add(new Square(1, 2));
listRoiB.add(new Square(1, 3));
listRoiB.add(new Square(1, 4));
listRoiB.add(new Square(2, 4));
listRoiB.add(new Square(3, 4));
listRoiB.add(new Square(3, 3));
listRoiB.add(new Square(3, 2));
listRoiB.add(new Square(2, 2));

       // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvementsRoiB = new HashSet<>(board.DeplacementsPossible(new Square(2,3))); 
 
       // Comparaison des deux listes 
       assertEquals(listRoiB, mouvementsRoiB); 
}
@Test
public void testPositionRookW(){
// Liste des mouvements possibles pour la tour blanche
Set<Square> listTourB = new HashSet<>();
listTourB.add(new Square(6, 7));
listTourB.add(new Square(7, 6));
listTourB.add(new Square(6, 5));
listTourB.add(new Square(6, 4));
listTourB.add(new Square(6, 3));
listTourB.add(new Square(6, 2));
listTourB.add(new Square(6, 1));
listTourB.add(new Square(6, 0));
listTourB.add(new Square(5, 6));
listTourB.add(new Square(4, 6));
listTourB.add(new Square(3, 6));
listTourB.add(new Square(2, 6));
listTourB.add(new Square(1, 6));
listTourB.add(new Square(0, 6));

       // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvementsTourB = new HashSet<>(board.DeplacementsPossible(new Square(6,6))); 
 
       // Comparaison des deux listes 
       assertEquals(listTourB, mouvementsTourB); 
}
@Test
public void testPositionKingB(){
// Liste des mouvements possibles pour le roi noir
Set<Square> listRoiN = new HashSet<>();
listRoiN.add(new Square(5, 7));
listRoiN.add(new Square(3, 7));

       // Création de la liste mouvement via la méthode testée 
       Set<Square> mouvementsRoiN = new HashSet<>(board.DeplacementsPossible(new Square(4,7))); 
 
       // Comparaison des deux listes 
       assertEquals(listRoiN, mouvementsRoiN); 
}




Ensuite, nous avons vérifier le comptage des coups et le nombre de pièces présentes pour déterminer l’état de la partie :

@Test
// Roi vs Roi
public void testPlatoManqueMateriel() {
// Blanc : La tour bouge
board.jouerUnCoup(new Move(new Square(6, 6), new Square(3, 6)));

// Noir : Le roi bouge
board.jouerUnCoup(new Move(new Square(4, 7), new Square(5, 7)));

// Blanc : La tour bouge
board.jouerUnCoup(new Move(new Square(3, 6), new Square(3, 7)));

// Noir : Le roi bouge
board.jouerUnCoup(new Move(new Square(5, 7), new Square(4, 6)));

// Blanc : La roi bouge
board.jouerUnCoup(new Move(new Square(2, 3), new Square(3, 3)));

// Noir : Le roi prend la tour
board.jouerUnCoup(new Move(new Square(4, 6), new Square(3, 7)));

// Vérification qu'il ne reste que deux pièces dans le plateau  
assertEquals(2,board.getPlato().size());

// Vérification du compteur de coups et demi-coups
assertEquals(3, board.rules.getNbCoup());
assertEquals(6, board.rules.getNbDemieCoup());
}



Enfin nous avons vérifier le bon fonctionnement de l’état échec et math :

@Test
// Echec et mat
public void testPlatoEchecEtMat() {

board.jouerUnCoup(new Move(new Square(2,3), new Square(3,4)));
board.jouerUnCoup(new Move(new Square(4,7), new Square(3,7)));

board.jouerUnCoup(new Move(new Square(3,4), new Square(3,5)));
board.jouerUnCoup(new Move(new Square(3,7), new Square(2,7)));

board.jouerUnCoup(new Move(new Square(3,5), new Square(2,5)));
board.jouerUnCoup(new Move(new Square(2,7), new Square(1,7)));

board.jouerUnCoup(new Move(new Square(2,5), new Square(1,5)));
board.jouerUnCoup(new Move(new Square(1,7), new Square(0,7)));

board.jouerUnCoup(new Move(new Square(6,6), new Square(6,7)));

assertEquals(new HashSet<>(), board.DeplacementsPossible(new Square(0,7)));

assertEquals(EtatPartie.FIN, board.rules.getPartie());

// Vérification du compteur de coups et demi-coups
assertEquals(4, board.rules.getNbCoup());
assertEquals(9, board.rules.getNbDemieCoup());
}



Bilan

Difficultés rencontrées

Réussites VS Ratés

Améliorations



6.2-Défis rencontrés

Pour les défis rencontrés, lors de la gestion des trajectoires nous avons dû implanter une List<List<Square>> pour les déplacements. Ensuite nous avons factoriser avec une classe abstraite Piece le code commun pour éviter l’arrêt a la première pièce rencontrée et pour assurer la cohérence entre toutes les pièces avec l’Interface IPiece, nous avons vérifié tous les cas limites (coins, bords, centre).



7.Améliorations possibles

A court terme :

A long terme nous pourrions implémenter Minimax ou une heuristique simple. Ajouter une interface utilisateur basique et un Support des fichiers PGN.



A long terme : Une table de transposition, étendre à un jeu d’échecs complet en ajoutant de nouvelles pièces.

8.Conclusion

Nous









Mode d’emploi

Brève Introduction

Le projet KRK consiste à développer un moteur d’échec qui est spécialisé dans les fins de parties de type (Roi + Tour contre Roi). Nous avons implémenté les règles de base, les déplacements des pièces, et la gestion complète du plateau.

Nous avons réussi à gérer le plateau 8*8, les déplacements légaux du Roi et de la Tour, le Système de tout par tour, la prise en compte des pièces adverses, les tests unitaires complets et le Pattern Factory pour l’initialisation.

Nos points forts dans notre Architecture est la séparation des responsabilités, nous avons des tests unitaires pour toutes les pièces, une bonne implémentation avec la List <List<Square>> et une facilité d’ajout de nouvelles pièces.

Pour l’instant nos défauts sont:



Liste Couche Clean Architecture

-Structure des packages

-Diagramme de classes simplifié

-Principes SOLID appliqués



Diagramme d’architecture



Listes de tests unitaires





Bilan du projet

Nous avons développé une base solide pour un moteur d'échecs KRK. L'architecture est claire, les tests sont complets, et le code est extensible. Les principes SOLID sont respectés, notamment la séparation des responsabilités et l'inversion des dépendances.

Nous avons appris l’importance d’une bonne architecture, la valeur des tests unitaires pour garantir la qualité, l’utilité des patterns de conception et la gestion des collections.

Difficultés :

Ce qui peut être amélioré :









Présentation globale

 	-Projet 

Le projet KRK consiste à développer un moteur d’échec qui est spécialisé dans les fins de parties de type (Roi + Tour contre Roi). Nous avons implémenté les règles de base:

-Un moteur de jeu complet avec règles des échecs

-Une interface UCI pour communiquer avec CuteChess

-Une architecture modulaire et extensible

-Des tests unitaires complets



-Guide utilisation

Pour utiliser notre jeu il faut ouvrir CuteChess



Présentation technique

-Clean Architecture

-Diagramme d’architecture

-SOLID

-Design Patterns

-Tests unitaires

Bilan

-Difficultés rencontrées

Nous avons eu des difficultés lors de l’implémentation de l’interface GUI de CuteChess car nous n’étions pas assez documenté et cela nous a pris plusieurs jours pour le faire fonctionner.

-Reussites VS Ratés

Nous avons reussi à gérer le plateau 8*8, les déplacements légaux du Roi et de la Tour, le Système de tout par tour, la prise en compte des pièces adverses, les tests unitaires complets, le Pattern Factory pour l’initialisation et la gestion de l’UI avec CuteChess.

Nos points forts dans notre Architecture est la séparation des responsabilités, nous avons des tests unitaires pour toutes les pièces, une bonne implémentation avec la List <List<Square>> et une facilité d’ajout de nouvelles pièces.

Par contre nous n’avons pas pus intégrer de stratégie de jeu automatique et la gestio des coups spéciaux.



-Améliorations

Nous devons améliorer le travail en groupe car il arrivait que 2 personnes code la même fonctionnalité sans s’en rendre compte.  

 

 

 

 

 