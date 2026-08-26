import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MoteurJeu {

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

    public void traiterDeplacement(Direction direction) {
        if (etat != Etat.EN_COURS) {
            return;
        }

        joueur.deplacer(direction);

        if (joueur.estArrive()) {
            etat = Etat.GAGNE;
        }
    }

    public ResultatRecherche obtenirIndice() {
        boolean[][] grid = labyrinthe.getGrille();
        int cols = grid[0].length;

        Graph graph = GraphToLabyrinthe.convertToGraph(grid);

        Point pos = joueur.getPosition();
        int startNode = pos.y * cols + pos.x;

        long debut = System.nanoTime();
        DijkstraAlgorithm solver = new DijkstraAlgorithm();
        List<Integer> nodePath = solver.findShortestPath(graph, startNode);
        long duree = System.nanoTime() - debut;

        List<int[]> mazePath = GraphToLabyrinthe.convertToMazePath(nodePath, cols);

        List<Point> chemin = new ArrayList<>();
        for (int[] rc : mazePath) {
            chemin.add(new Point(rc[1], rc[0])); // rc = {row, col} -> Point(x, y)
        }

        int cout = Math.max(0, chemin.size() - 1);
        return new ResultatRecherche(chemin, chemin, duree, cout);
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