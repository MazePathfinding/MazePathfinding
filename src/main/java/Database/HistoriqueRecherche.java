

import java.awt.Point;
import java.util.List;


public class HistoriqueRecherche {

//    
    private final int id;
    private final String algorithme;      
    private final List<Point> chemin;     
    private final int longueurChemin;     
    private final int cout;               
    private final double tempsExecutionMs;
    private final int noeudsExplores;
    private final String dateRecherche;   

    public HistoriqueRecherche(int id, String algorithme, List<Point> chemin, int longueurChemin,
                                int cout, double tempsExecutionMs, int noeudsExplores, String dateRecherche) {
        this.id = id;
        this.algorithme = algorithme;
        this.chemin = chemin;
        this.longueurChemin = longueurChemin;
        this.cout = cout;
        this.tempsExecutionMs = tempsExecutionMs;
        this.noeudsExplores = noeudsExplores;
        this.dateRecherche = dateRecherche;
    }

    public int getId() { return id; }
    public String getAlgorithme() { return algorithme; }
    public List<Point> getChemin() { return chemin; }
    public int getLongueurChemin() { return longueurChemin; }
    public int getCout() { return cout; }
    public double getTempsExecutionMs() { return tempsExecutionMs; }
    public int getNoeudsExplores() { return noeudsExplores; }
    public String getDateRecherche() { return dateRecherche; }

    @Override
    public String toString() {
        return String.format(
            "[%d] %s | longueur=%d | cout=%d | temps=%.2fms | noeuds=%d | %s",
            id, algorithme, longueurChemin, cout, tempsExecutionMs, noeudsExplores, dateRecherche
        );
    }
}
