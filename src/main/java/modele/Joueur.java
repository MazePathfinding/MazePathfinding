package modele;

import java.awt.Point;

public class Joueur {

    private Point position;
    private final Labyrinthe labyrinthe;

    public Joueur(Labyrinthe labyrinthe) { // Constructeur avec paramètre
        this.labyrinthe = labyrinthe;
        this.position = new Point(labyrinthe.getEntree());
    }

    public boolean deplacer(Direction direction) { // Type de retour = true si le déplacement a réussi, et false si il y a un mur et le joueur ne bouge pas
        int nx = position.x + direction.dx;
        int ny = position.y + direction.dy;

        if (labyrinthe.estMur(nx, ny)) {
            return false; // déplacement refusé
        }

        position = new Point(nx, ny);
        return true;
    }

    public Point getPosition() { // Constructeur
        return position;
    }

    public boolean estArrive() { // Fin du jeu
        return position.equals(labyrinthe.getSortie());
    }
}
