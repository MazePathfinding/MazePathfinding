package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import modele.Direction;
import modele.Labyrinthe;
import modele.MoteurJeu;
import modele.ResultatRecherche;

/**
 *
 * @author fenit
 */
public class PanneauJeu extends JPanel {

    // Taille d'une case en pixels
    private final int CELL_SIZE = 35;
    // Couleurs principales du labyrinthe
    private final Color COULEUR_FOND = new Color(8, 13, 22);
    private final Color COULEUR_MUR = new Color(9, 15, 25);
    private final Color COULEUR_PASSAGE = new Color(20, 30, 46);
    private final Color COULEUR_BORDURE = new Color(36, 52, 74);
    // Couleur utilisée pour les noeuds explorés
    private Color couleurExploration = new Color(15, 78, 88);
    private final Color COULEUR_CHEMIN = new Color(91, 70, 22);
    private MoteurJeu moteurJeu;
    private Labyrinthe labyrinthe;
    // Dimensions du labyrinthe
    private int ROWS;
    private int COL;
    // true = mur, false = passage
    private boolean[][] maze;
    private Point start;
    // Point d'arrivée
    private Point goal;
    private List<Point> path = new ArrayList<>();
    private List<Point> exploredNodes = new ArrayList<>();
    // Timer utilisé pour l'animation des noeuds explorés
    private Timer timerRecherche;
    // Timer utilisé pour le déplacement du joueur sur le chemin
    private Timer timerChemin;

    public PanneauJeu(MoteurJeu moteurJeu) {
        this.moteurJeu = moteurJeu;
        this.labyrinthe = moteurJeu.getLabyrinthe();
        this.ROWS = labyrinthe.getHauteur();
        this.COL = labyrinthe.getLargeur();
        this.maze = labyrinthe.getObstacles();
        this.start = labyrinthe.getEntree();
        this.goal = labyrinthe.getSortie();

        // Calcul de la taille du panneau
        int width = COL * CELL_SIZE;
        int height = ROWS * CELL_SIZE;

        this.setPreferredSize(new java.awt.Dimension(width, height));
        this.setBackground(COULEUR_FOND);
    }

    // Dessine tous les éléments du jeu
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawMaze(g2);
        drawExploredNodes(g2);
        drawPath(g2);
        drawStartAndGoal(g2);
        drawPlayer(g2);
        drawGrid(g2);
    }

    // Dessine les murs du labyrinthe
    private void drawMaze(Graphics2D g2) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COL; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                if (maze[row][col]) {
                    // Mur
                    g2.setColor(COULEUR_MUR);

                } else {
                    // Passage
                    g2.setColor(COULEUR_PASSAGE);
                }
                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    // Dessine les nœuds explorés par l'algorithme
    private void drawExploredNodes(Graphics2D g2) {
        g2.setColor(couleurExploration);

        for (Point p : exploredNodes) {
            int x = p.x * CELL_SIZE;
            int y = p.y * CELL_SIZE;

            g2.fillRect(x + 7, y + 7, CELL_SIZE - 14, CELL_SIZE - 14);
        }
    }

    // Dessine le chemin final
    private void drawPath(Graphics2D g2) {
        g2.setColor(COULEUR_CHEMIN);

        for (Point p : path) {
            int x = p.x * CELL_SIZE;
            int y = p.y * CELL_SIZE;
            g2.fillRect(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
        }
    }

    // Dessine les cases de départ et d'arrivée avec leurs lettres
    private void drawStartAndGoal(Graphics2D g2) {
        // Case de départ
        int startX = start.x * CELL_SIZE;
        int startY = start.y * CELL_SIZE;

        g2.setColor(new Color(10, 88, 45));
        g2.fillRect(startX, startY, CELL_SIZE, CELL_SIZE);

        // Lettre S
        g2.setColor(new Color(70, 255, 140));
        g2.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));

        g2.drawString("S", startX + (CELL_SIZE / 2) - 5, startY + (CELL_SIZE / 2) + 5);

        // Case d'arrivée
        int goalX = goal.x * CELL_SIZE;
        int goalY = goal.y * CELL_SIZE;

        g2.setColor(new Color(105, 20, 28));
        g2.fillRect(goalX, goalY, CELL_SIZE, CELL_SIZE);

        // Lettre E
        g2.setColor(new Color(255, 110, 110));

        g2.drawString("E", goalX + (CELL_SIZE / 2) - 5, goalY + (CELL_SIZE / 2) + 5);
    }

    // Dessine le joueur
    private void drawPlayer(Graphics2D g2) {
        // Position actuelle récupérée depuis le backend
        Point player = moteurJeu.getJoueur().getPosition();

        int x = player.x * CELL_SIZE;
        int y = player.y * CELL_SIZE;

        g2.setColor(Color.BLUE);
        g2.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
    }

    // Dessine les lignes de la grille
    private void drawGrid(Graphics2D g2) {
        // Couleur des lignes qui séparent les cases
        g2.setColor(COULEUR_BORDURE);
        // Dessine les lignes verticales de la grille
        for (int col = 0; col <= COL; col++) {
            int x = col * CELL_SIZE;
            g2.drawLine(x, 0, x, ROWS * CELL_SIZE);
        }

        // Dessine les lignes horizontales de la grille
        for (int row = 0; row <= ROWS; row++) {
            int y = row * CELL_SIZE;
            g2.drawLine(0, y, COL * CELL_SIZE, y);
        }
    }

    // Reçoit le chemin final
    public void setPath(List<Point> newPath) {
        this.path = newPath;
        repaint();
    }

    // Reçoit les nœuds explorés
    public void setExploredNodes(List<Point> newExploredNodes) {
        this.exploredNodes = newExploredNodes;
        repaint();
    }

    // Anime le déplacement du joueur sur le chemin final
    public void animerChemin(List<Point> chemin, Runnable finAnimation) {
        // Le premier point correspond déjà à la position de départ
        final int[] index = {1};
        timerChemin = new Timer(200, e -> {
            // Arrête l'animation lorsque le chemin est terminé
            if (index[0] >= chemin.size()) {
                timerChemin.stop();

                if (finAnimation != null) {
                    finAnimation.run();
                }
                return;
            }
            // Position actuelle du joueur
            Point positionActuelle = moteurJeu.getJoueur().getPosition();

            // Prochaine position à atteindre
            Point prochainePosition = chemin.get(index[0]);

            // Calcul du déplacement
            int dx = prochainePosition.x - positionActuelle.x;
            int dy = prochainePosition.y - positionActuelle.y;
            Direction direction = null;

            // Détermine la direction à suivre
            if (dx == 1) {
                direction = Direction.DROITE;

            } else if (dx == -1) {
                direction = Direction.GAUCHE;

            } else if (dy == 1) {
                direction = Direction.BAS;

            } else if (dy == -1) {
                direction = Direction.HAUT;
            }

            // Demande au backend de déplacer le joueur
            if (direction != null) {
                moteurJeu.traiterDeplacement(direction);
            }
            // Redessine le joueur
            repaint();
            index[0]++;
        });
        timerChemin.start();
    }

    // Anime progressivement les nœuds explorés pendant la recherche
    public void animerRecherche(List<Point> noeudsExplores, List<Point> chemin,
            ResultatRecherche resultat, FenetreJeu fenetre, Runnable finAnimation) {

        exploredNodes.clear();
        path.clear();

        final int[] index = {0};

        timerRecherche = new Timer(50, e -> {
            if (index[0] >= noeudsExplores.size()) {
                timerRecherche.stop();
                // Affiche maintenant le chemin final
                setPath(chemin);

                // Affiche les statistiques seulement après la recherche
                fenetre.setNoeudsExplores(resultat.getNoeudsExplores());
                fenetre.setLongueurChemin(resultat.getLongueurChemin());
                fenetre.setCout(resultat.getCout());
                fenetre.setTempsExecution(resultat.getTempsExecutionMs());

                // Déplace ensuite le joueur
                animerChemin(chemin, finAnimation);
                return;
            }

            exploredNodes.add(new Point(noeudsExplores.get(index[0])));

            repaint();
            index[0]++;
        });
        timerRecherche.start();
    }

    // Arrete les animations et reinitialise l'affichage de la recherche 
    public void reinitialiserRecherche() {
        if (timerRecherche != null && timerRecherche.isRunning()) {
            timerRecherche.stop();
        }

        if (timerChemin != null && timerChemin.isRunning()) {
            timerChemin.stop();
        }

        exploredNodes.clear();
        path.clear();
        repaint();
    }

    // Change la couleur des noeuds explorés
    public void setCouleurExploration(Color couleur) {
        this.couleurExploration = couleur;
        repaint();
    }

    public MoteurJeu getMoteurJeu() {
        return moteurJeu;
    }

}
