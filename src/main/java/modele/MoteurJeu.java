package modele;

import algo.Dijkstra;
import algo.AStar;
import algo.RechercheAveugle;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MoteurJeu { // Reçoit les déplacements du joueur et met à jour l'état du jeu

    public enum Etat {
        EN_COURS,
        GAGNE
    }

    public enum Algorithme {
        DIJKSTRA,
        A_STAR,
        RECHERCHE_AVEUGLE
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
        return obtenirIndice(Algorithme.DIJKSTRA);
    }

    public ResultatRecherche obtenirIndice(Algorithme algo) {
        switch (algo) {
            case A_STAR:
                return AStar.calculerChemin(labyrinthe.getObstacles(), joueur.getPosition(), labyrinthe.getSortie());
            case RECHERCHE_AVEUGLE:
                return RechercheAveugle.calculerChemin(labyrinthe.getObstacles(), joueur.getPosition(), labyrinthe.getSortie());
            default:
                return Dijkstra.calculerChemin(labyrinthe.getObstacles(), joueur.getPosition(), labyrinthe.getSortie());
        }
    }

    public Map<Algorithme, ResultatRecherche> comparerAlgorithmes() {
        Map<Algorithme, ResultatRecherche> resultats = new HashMap<>();
        resultats.put(Algorithme.DIJKSTRA, obtenirIndice(Algorithme.DIJKSTRA));
        resultats.put(Algorithme.A_STAR, obtenirIndice(Algorithme.A_STAR));
        resultats.put(Algorithme.RECHERCHE_AVEUGLE, obtenirIndice(Algorithme.RECHERCHE_AVEUGLE));
        return resultats;
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