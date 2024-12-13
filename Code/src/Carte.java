import java.awt.Color;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import libraries.StdDraw;

public class Carte {
    private String nom;
    private String path;
    private ArrayList<ArrayList<String>> tableau;
    protected ArrayList<ArrayList<String>> mapLu;
    private int nbDeColone;
    private int nbDeLigne;
    private Double tailleCase;
    private Double creuxX;
    private Double creuxY;
    static boolean gameIsPlaying;
    static int PVBase = 3;
    private ArrayList<ArrayList<Case>> grille;

    public Carte(String nom) {
        this.nom = nom;
        this.path = System.getProperty("user.dir") + "/Code/resources/resources/maps/" + nom + ".mtp";
        this.tableau = new ArrayList<>();
        this.mapLu = this.mettreDansTab();
        this.nbDeColone = mapLu.size();
        this.nbDeLigne = mapLu.get(0).size();
        if (nbDeColone > nbDeLigne) {
            this.tailleCase = (double) (700 / nbDeColone);
            this.creuxX = (double) (((double) nbDeColone - nbDeLigne) / 2) * tailleCase;
            this.creuxY = 0.0;
        } else {
            this.tailleCase = (double) (700 / nbDeLigne);
            this.creuxX = 0.0;
            this.creuxY = (double) (((double) nbDeLigne - nbDeColone) / 2) * tailleCase;
        }
        this.gameIsPlaying = true;
        this.grille = new ArrayList<>();
    }
    public ArrayList<ArrayList<String>> getMapLu(){
        return mapLu;
    }
    /**
     * Méthode pour lire le fichier et extraire son contenu en tableau à double entrée.
     *
     * @return Une liste de listes représentant le contenu de la carte.
     * @throws IOException Si le fichier n'est pas trouvé ou ne peut pas être lu.
     */
    public ArrayList<ArrayList<String>> lireCarte() throws IOException {
        Path filePath = Paths.get(this.path); // Création du chemin d'accès au fichier
        
        // Vérification de l'existence du fichier
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Le fichier " + this.path + " n'existe pas.");
        }
        // Lecture ligne par ligne du fichier
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                // Divise chaque ligne en une liste de caractères et crée une ArrayList modifiable
                ArrayList<String> ligneTableau = new ArrayList<>(Arrays.asList(ligne.split("")));
                tableau.add(ligneTableau);
            }
        }

        return tableau;
    }
    public String getNom() {
        return nom;
    }
    public ArrayList<ArrayList<String>> getTableau(){
        return tableau;
    }
    public ArrayList<ArrayList<String>> mettreDansTab(){
        try {
            ArrayList<ArrayList<String>> contenu = this.lireCarte();
            return contenu;
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return null;
    }
    public void afficher (){
        for (ArrayList<String> ligne : mapLu)
            System.out.println(ligne);
    }

    public void creerCase() {
        for (int i = 0; i < mapLu.size(); i++) {
            ArrayList<Case> ligne = new ArrayList<>();
            for (int j = 0; j < mapLu.get(i).size(); j++) {
                double X = creuxX + (tailleCase / 2) + (j * tailleCase);
                double Y = -creuxY + 700 - tailleCase / 2 - (i * tailleCase);
                char lettre = mapLu.get(i).get(j).charAt(0);
                Case c = new Case(X, Y, lettre);
                ligne.add(c);
                // Dessiner la case
                if (lettre == 'X') {
                    StdDraw.setPenColor(new Color(11, 102, 35));
                } else if (lettre == 'S') {
                    StdDraw.setPenColor(Color.RED);
                } else if (lettre == 'B') {
                    StdDraw.setPenColor(Color.ORANGE);
                } else if (lettre == 'R') {
                    StdDraw.setPenColor(new Color(194, 178, 128));
                } else {
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                }
                StdDraw.filledSquare(X, Y, (tailleCase / 2) - 1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.rectangle(X, Y, tailleCase / 2.0, tailleCase / 2.0);
            }
            grille.add(ligne);
        }
        StdDraw.show();  // N'oubliez pas de mettre à jour l'affichage
    }
    public Case detecterCase(double mouseX, double mouseY) {
        for (ArrayList<Case> ligne : grille) {
            for (Case c : ligne) {
                if (c.contains(mouseX, mouseY, tailleCase)) {
                    return c;
                }
            }
        }
        return null;
    }
    public void detectionSouris() { //chat GPT m'a un petit peu aider pour comprendre comment recuperer certaine information de l'ArrayList<ArrayList<Case>>
        if (PVBase <= 0) {
            gameIsPlaying = false;
            return;
        }
        while (gameIsPlaying) {
            if (StdDraw.isMousePressed()) {
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();
                // Détecter la case sous la souris
                Case caseDetectee = detecterCase(mouseX, mouseY);
                if (caseDetectee != null) {
                    // Contour jaune
                    StdDraw.setPenColor(Color.YELLOW);
                    StdDraw.rectangle(caseDetectee.getX(), caseDetectee.getY(), tailleCase / 2.0, tailleCase / 2.0);
                    StdDraw.show();
                    System.out.println("Case cliquée : Lettre = " + caseDetectee.getLettre() + ", X = " + caseDetectee.getX() + ", Y = " + caseDetectee.getY());
                    // Attendre que le clic soit relâché
                    while (StdDraw.isMousePressed()) {// Bloque jusqu'à relâcher
                    }
                    // Rendre le contour noir
                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.rectangle(caseDetectee.getX(), caseDetectee.getY(), tailleCase / 2.0, tailleCase / 2.0);
                    StdDraw.show();
                }
            }
        }
    }
    
    public Case chercherSpawn() {
        for (ArrayList<Case> ligne : grille) {
            for (Case c : ligne) {
                if (c.getLettre() == 'S') {
                    return c;
                }
            }
        }
        return null;
    }
    public List<Case> construitCheminRecursive(Case current, List<Case> chemin) { //chatGPT m'a aider a mieux comprendre comment faire pour uniquement chercher les case a gauche,en haut,en bas et a droite
        if (current.getLettre() == 'B') {
            chemin.add(current);
            return chemin;
        }
        
        chemin.add(current);
        Case next = trouveCaseSuivante(current , chemin);
        return construitCheminRecursive(next, chemin);
    }
    public Case trouveCaseSuivante(Case current, List<Case> chemin) {
        // Variables pour gérer les déplacements autour de chaque case (haut, bas, gauche, droite)
        double[] dx = {-tailleCase, tailleCase, 0.0, 0.0};  // Mouvement en X (haut, bas)
        double[] dy = {0.0, 0.0, -tailleCase, tailleCase};  // Mouvement en Y (gauche, droite)
        for (int i = 0; i < 4; i++) {
            double nx = current.getX() + dx[i];  // Déplacement en X avec Double
            double ny = current.getY() + dy[i];  // Déplacement en Y avec Double
            Case neighbor = detecterCase(nx, ny);
            if ((!(chemin.contains(neighbor)) && neighbor.getLettre() == 'R' || neighbor.getLettre() == 'B')) {
                return neighbor; 
            }
        }
        return null;
    }
    public List<Case> construitChemin() {
        List<Case> chemin = new ArrayList<>();
        Case spawn = chercherSpawn();

        if (spawn != null) {
            return construitCheminRecursive(spawn, chemin);
        }
        return chemin;
    }
}