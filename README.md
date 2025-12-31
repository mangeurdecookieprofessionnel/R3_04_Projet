
Maxime COURDAVAULT       208
Tristan PIOTON       208
Moussa WAGUE       207
Jean-François DENA       208

PROJET R3.04 : Projet Jeu d’échec

## 1. Présentation Globale
### 1.1 Le Projet
Le projet consiste à développer et tester un programme permettant  à deux joueurs de jouer une finale de type KRK aux échecs, c’est à dire une fin de partie dans laquelle les blancs ont un roi (King) et une tour (Rook) et les noirs un roi.
Le projet porte sur les principes SOLID, la Clean architecture, la programmation dirigée par les tests, le travail en équipe, et la gestion des versions avec git. Le GUI (Graphical User Interface) est fourni par un outil externe : CuteChess, via le protocole UCI.

### 1.2 Mode d'emploi
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

### 1.3 État du projet
**Ce qui est Fonctionnel** :
- Moteur de jeu KRK complet
- Interface UCI avec CuteChess
- Détection d'échec et mat
- Règle des 50 coups
- Gestion du pat
- 
## 2. Présentation Technique
### 2.1 Clean Architecture

Notre projet suit rigoureusement la Clean Architecture avec 4 couches :

#### **Couche Domain (Entities)**
-entities.board.Board : Gestion complète du plateau d'échecs 8×8
-entities.board.IPiece : Interface définissant le contrat de toutes les pièces
-entities.board.Square : Record immuable représentant une case (x, y)
-entities.board.Move : Record immuable représentant un coup (départ → arrivée)
-entities.pieces.Piece : Classe abstraite avec implémentation commune à toutes les pièces
-entities.pieces.King : Implémentation spécifique du roi (déplacements sur 8 cases)
-entities.pieces.Rook : Implémentation spécifique de la tour (déplacements en ligne droite)
-entities.modeles.Couleur : Enumération WHITE/BLACK pour les couleurs des pièces
-entities.modeles.EtatPartie : Enumération JEU/NULLE/FIN pour l'état de la partie
Justification : Cette couche contient les concepts métier fondamentaux qui changent rarement. Les records Square et Move garantissent l'immutabilité, l'interface IPiece permet le polymorphisme.

#### **Couche Application (Use Cases)**
-useCases.MoveGeneratorUC : Génère la liste de tous les coups possibles pour un joueur donné
-useCases.MoveValidatorUC : Valide si un coup est légal (vérifie notamment les échecs au roi)
-useCases.MoveSelectorUC : Sélectionne le meilleur coup parmi ceux possibles
Justification : Cette couche implémente les règles spécifiques au jeu KRK. Chaque usecase a une responsabilité unique. Ils dépendent uniquement du Domain Layer.

#### **Couche Interface (Adapters)**
-adapters.UCIAdapter : Implémentation complète du protocole UCI (Universal Chess Interface) pour communiquer avec CuteChess
Justification : Cet adapteur isole notre logique métier des détails du protocole UCI. Si nous changions d'interface, seul ce package serait affecté.

#### **Couche Infrastructure**
-factory.PieceFactory : Implémentation du pattern Factory pour créer les configurations initiales de pièces
Justification : Ce pattern centralise la création d'objets complexes. Permet de changer facilement la configuration initiale sans modifier le code métier.

### 2.2 Diagramme d'architecture
<img width="1714" height="720" alt="Diagramme de classe projet_krk_echec" src="https://github.com/user-attachments/assets/4d8a6010-791a-45e6-b270-9851be598358" />

### 2.3 Principes SOLID
### Principes SOLID respectés

#### **Single Responsibility Principle**
- `Board` : Gère uniquement le plateau
- `Rules` : Gère uniquement les règles
- `UCIAdapter` : Gère uniquement la communication UCI

#### **Open/Closed Principle**
// Ouvert à l'extension
public interface IPiece {
    // Nouvelle pièce = nouvelle classe implémentant IPiece
}
// King et Rook peuvent remplacer Piece partout
IPiece piece = new King(Couleur.WHITE);
IPiece piece2 = new Rook(Couleur.BLACK);
Interface Segregation Principle
Interface IPiece minimale et cohérente

Pas de méthodes inutiles pour certaines pièces

Dependency Inversion Principle
public class Board {
    private Map<Square, IPiece> plato;  // Dépend de l'abstraction
    // Pas de dépendance directe à King ou Rook
}

### 2.4 Design Patterns
### **Design Patterns - IDENTIFIER** :
markdown
### Design Patterns utilisés

**Factory Pattern** (`PieceFactory`)
- Centralise la création des configurations initiales
- Permet de changer facilement la position de départ

**Template Method Pattern** (`Piece` classe abstraite)
- Méthode `aplatir()` implémentée dans la classe mère
- Méthode `mouvement()` abstraite à spécialiser

**Strategy Pattern** (en émergence dans `MoveSelectorUC`)
- Pourrait utiliser différentes stratégies de sélection
### 2.5 Tests unitaires
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

## 3. Bilan
### 3.1 Réussites vs Limites
Nous avons reussi a respecté la Clean Architecture et SOLID, la Communication complète avec CuteChess, Echec/mat, pat, 50 coups, le manque de matériel, la gestion du plateau, des règles du jeu, des pièces, tout nos tests sont passés et le code est extensible et documenté.
La gestion de l'interface de collaboration Github.
Nous avons appris l’importance d’une bonne architecture, la valeur des tests unitaires pour garantir la qualité, l’utilité des patterns de conception et la gestion des collections.
Par contre nous n’avons pas pus intégrer de stratégie de jeu automatique, de gestion des coups spéciaux et d'algorithme Minimax.

### 3.2 Difficultés rencontrées
Nous avons eu des difficultés lors de l’implémentation de l’interface GUI de CuteChess car nous n’étions pas assez documenté et cela nous a pris plusieurs jours pour le faire fonctionner.

### 3.3 Perspectives d'amélioration
Nous devons améliorer le travail en groupe car il arrivait que 2 personnes code la même fonctionnalité sans s’en rendre compte. 
Cette base solide nous permettrait d'ajouter facilement des fonctionnalités avancées come un algorithme d'IA.


 

 

 

 
