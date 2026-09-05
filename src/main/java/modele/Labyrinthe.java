package modele;

import java.awt.Point;

/**
 *
 * @author fenit
 */
public class Labyrinthe {
    private static final int LARGEUR = 23;
    private static final int HAUTEUR = 15;
    
    // true = mur, false = passage
    private final boolean[][] grille;

    // Point de départ
    private final Point entree;

    // Point d'arrivée
    private final Point sortie;

    // Constructeur
    public Labyrinthe() {

        // Grille
        this.grille = genererLabyrinthe();

        // Position du départ
        this.entree = new Point(1, 1);
        // Position de l'arrivée
        this.sortie = new Point(LARGEUR - 2, HAUTEUR - 2);
    }

    // Vérifie si une case est un mur ou hors de la grille
    public boolean estMur(int x, int y) {

        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur()) {
            return true;
        }

        return grille[y][x];
    }
    private boolean[][] genererLabyrinthe() {
        boolean[][] g = new boolean[HAUTEUR][LARGEUR];
        for (boolean[] ligne : g) {
            Arrays.fill(ligne, true); // tout est mur au départ
        }
 
        Random random = new Random();
        Deque<Point> pile = new ArrayDeque<>();
 
        Point depart = new Point(1, 1);
        g[depart.y][depart.x] = false;
        pile.push(depart);
 
        // Déplacements de 2 cases (pour garder un mur entre deux passages)
        int[] dx = {0, 0, -2, 2};
        int[] dy = {-2, 2, 0, 0};
 
        while (!pile.isEmpty()) {
            Point actuel = pile.peek();
 
            List<Integer> directions = new ArrayList<>(List.of(0, 1, 2, 3));
            Collections.shuffle(directions, random);
 
            boolean aAvance = false;
 
            for (int dir : directions) {
                int nx = actuel.x + dx[dir];
                int ny = actuel.y + dy[dir];
 
                boolean dansLesLimites = nx > 0 && nx < LARGEUR - 1 && ny > 0 && ny < HAUTEUR - 1;
 
                if (dansLesLimites && g[ny][nx]) {
                    // Casse le mur situé entre la case actuelle et la voisine
                    g[actuel.y + dy[dir] / 2][actuel.x + dx[dir] / 2] = false;
                    g[ny][nx] = false;
 
                    pile.push(new Point(nx, ny));
                    aAvance = true;
                    break;
                }
            }
            if (!aAvance) {
                pile.pop(); // aucune voisine disponible, on revient en arrière
            }
        }
        return g;
    }

    // Vérifie si une case est un mur ou hors de la grille
    public boolean estMur(int x, int y) {
 
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur()) {
            return true;
        }
 
        return grille[y][x];
    }

    // Retourne la grille
    public boolean[][] getObstacles() {
        return grille;
    }

    // Retourne le point de départ
    public Point getEntree() {
        return entree;
    }

    // Retourne le point d'arrivée
    public Point getSortie() {
        return sortie;
    }

    // Retourne la largeur du labyrinthe
    public int getLargeur() {
        return grille[0].length;
    }

    // Retourne la hauteur du labyrinthe
    public int getHauteur() {
        return grille.length;
    }
}
