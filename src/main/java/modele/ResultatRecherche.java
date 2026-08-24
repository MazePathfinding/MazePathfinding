package modele;

import java.awt.Point;
import java.util.List;

public class ResultatRecherche { // Affiche le resultat final après le calcul de dijsktra

    private final List<Point> chemin;            // chemin final trouvé, dans l'ordre
    private final List<Point> ordreExploration;   // toutes les cases visitées, dans l'ordre de visite
    private final long tempsExecutionNanoSecondes;
    private final int cout;                       // -1 si aucun chemin trouvé

    public ResultatRecherche(List<Point> chemin, List<Point> ordreExploration, long tempsExecutionNanoSecondes, int cout) {
        this.chemin = chemin;
        this.ordreExploration = ordreExploration;
        this.tempsExecutionNanoSecondes = tempsExecutionNanoSecondes;
        this.cout = cout;
    }

    // Le chemin final trouvé (le plus court)
    public List<Point> getChemin() {
        return chemin;
    }

    // Toutes les cases explorées par l'algorithme, dans l'ordre exact où il les a visitées.
    public List<Point> getOrdreExploration() {
        return ordreExploration;
    }

    // Nombre total de cases explorées 
    public int getNoeudsExplores() {
        return ordreExploration.size();
    }

    // Temps d'exécution en millisecondes (valeur décimale)
    public double getTempsExecutionMs() {
        return tempsExecutionNanoSecondes / 1_000_000.0;
    }

    public int getCout() {
        return cout;
    }

    // Longueur du chemin final, en nombre de cases (départ inclus).
    public int getLongueurChemin() {
        return chemin.size();
    }
}
