/**
 * Les 4 directions de déplacement possibles dans le labyrinthe.
 * Chaque direction porte son propre décalage (dx, dy) pour simplifier
 * le calcul de la case suivante.
 */
public enum Direction { // Renvoie les direction possibles dans le labyrinthe; x ou y ; +1 ou -1
    HAUT(0, -1),
    BAS(0, 1),
    GAUCHE(-1, 0),
    DROITE(1, 0);

    public final int dx;
    public final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
