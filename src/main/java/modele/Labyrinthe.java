package modele;

import java.awt.Point;

/**
 *
 * @author fenit
 */
public class Labyrinthe {

    // true = mur, false = passage
    private final boolean[][] grille;

    // Point de départ
    private final Point entree;

    // Point d'arrivée
    private final Point sortie;

    // Constructeur
    public Labyrinthe() {

        // Grille
        this.grille = new boolean[][]{
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, false, true, true, true, true, true, true, false, true, true, true, true, true, true, false, true, true, false, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
            {true, true, false, true, true, true, true, false, true, true, true, true, true, true, false, true, true, true, true, false, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, false, true, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, false, true, true, true, true, true, true, true, false, true, true, true, false, true, true, true, false, true, true, true, true},
            {true, false, false, false, false, false, true, true, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, false, true, true, true, true, true, true, false, true, true, true, true, true, true, false, true, true, true, true, false, true, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, true, true, false, true, true, true, false, true, false, true, false, true, true, true},
            {true, false, false, false, false, false, true, true, false, true, true, true, false, false, false, false, false, false, false, true, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}
        };

        // Position du départ
        this.entree = new Point(1, 1);
        // Position de l'arrivée
        this.sortie = new Point(22, 11);
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
