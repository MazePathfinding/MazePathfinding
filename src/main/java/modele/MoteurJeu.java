package modele;

import algo.Dijkstra;

public class MoteurJeu { // Reçoit les déplacements du joueur et met à jour l'état du jeu

    public enum Etat {
        EN_COURS,
        GAGNE
    }

    private final Labyrinthe labyrinthe;
    private final Joueur joueur;
    private Etat etat;

    public MoteurJeu(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        this.joueur = new Joueur(labyrinthe);
        this.etat = Etat.EN_COURS;
    }

    public void traiterDeplacement(Direction direction) { // Appelé par le front à chaque touche pressée par le joueur. Va vers la direction
        if (etat != Etat.EN_COURS) {
            return;
        }

        joueur.deplacer(direction);

        if (joueur.estArrive()) {
            etat = Etat.GAGNE;
        }
    }

    public ResultatRecherche obtenirIndice() {
        return Dijkstra.calculerChemin(
            labyrinthe.getObstacles(),
            joueur.getPosition(),
            labyrinthe.getSortie()
        );
    }

    public Etat getEtat() {
        return etat;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
