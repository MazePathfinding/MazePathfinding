import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author fenit
 */
public class PanneauJeu extends JPanel {

    // Taille d'une case en pixels
    private final int CELL_SIZE = 30;

    // Moteur du jeu
    private MoteurJeu moteurJeu;

    // Labyrinthe récupéré depuis le moteur
    private Labyrinthe labyrinthe;

    // Dimensions du labyrinthe
    private int ROWS;
    private int COL;

    // true = mur, false = passage
    private boolean[][] maze;

    // Point de départ
    private Point start;

    // Point d'arrivée
    private Point goal;

    // Chemin final trouvé par l'algorithme
    private List<Point> path = new ArrayList<>();

    // Nœuds explorés déjà affichés
    private List<Point> exploredNodes = new ArrayList<>();

    public PanneauJeu(MoteurJeu moteurJeu) {

        // Récupération du moteur
        this.moteurJeu = moteurJeu;

        // Récupération du labyrinthe
        this.labyrinthe = moteurJeu.getLabyrinthe();

        // Récupération des dimensions
        this.ROWS = labyrinthe.getHauteur();
        this.COL = labyrinthe.getLargeur();

        // Récupération des murs
        this.maze = labyrinthe.getObstacles();

        // Récupération du départ et de l'arrivée
        this.start = labyrinthe.getEntree();
        this.goal = labyrinthe.getSortie();

        // Calcul de la taille du panneau
        int width = COL * CELL_SIZE;
        int height = ROWS * CELL_SIZE;

        this.setPreferredSize(
                new java.awt.Dimension(width, height)
        );

        this.setBackground(Color.WHITE);
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

                    g2.setColor(Color.DARK_GRAY);

                    g2.fillRect(
                            x,
                            y,
                            CELL_SIZE,
                            CELL_SIZE
                    );
                }
            }
        }
    }

    // Dessine les nœuds explorés par l'algorithme
    private void drawExploredNodes(Graphics2D g2) {

        g2.setColor(Color.CYAN);

        for (Point p : exploredNodes) {

            int x = p.x * CELL_SIZE;
            int y = p.y * CELL_SIZE;

            g2.fillRect(
                    x + 7,
                    y + 7,
                    CELL_SIZE - 14,
                    CELL_SIZE - 14
            );
        }
    }

    // Dessine le chemin final
    private void drawPath(Graphics2D g2) {

        g2.setColor(Color.YELLOW);

        for (Point p : path) {

            int x = p.x * CELL_SIZE;
            int y = p.y * CELL_SIZE;

            g2.fillRect(
                    x + 5,
                    y + 5,
                    CELL_SIZE - 10,
                    CELL_SIZE - 10
            );
        }
    }

    // Dessine le départ et l'arrivée
    private void drawStartAndGoal(Graphics2D g2) {

        // Départ
        int startX = start.x * CELL_SIZE;
        int startY = start.y * CELL_SIZE;

        g2.setColor(Color.GREEN);

        g2.fillRect(
                startX,
                startY,
                CELL_SIZE,
                CELL_SIZE
        );

        // Arrivée
        int goalX = goal.x * CELL_SIZE;
        int goalY = goal.y * CELL_SIZE;

        g2.setColor(Color.RED);

        g2.fillRect(
                goalX,
                goalY,
                CELL_SIZE,
                CELL_SIZE
        );
    }

    // Dessine le joueur
    private void drawPlayer(Graphics2D g2) {

        // Position actuelle récupérée depuis le backend
        Point player = moteurJeu.getJoueur().getPosition();

        int x = player.x * CELL_SIZE;
        int y = player.y * CELL_SIZE;

        g2.setColor(Color.BLUE);

        g2.fillOval(
                x + 5,
                y + 5,
                CELL_SIZE - 10,
                CELL_SIZE - 10
        );
    }

    // Dessine les lignes de la grille
    private void drawGrid(Graphics2D g2) {

        g2.setColor(Color.LIGHT_GRAY);

        // Lignes verticales
        for (int col = 0; col <= COL; col++) {

            int x = col * CELL_SIZE;

            g2.drawLine(
                    x,
                    0,
                    x,
                    ROWS * CELL_SIZE
            );
        }

        // Lignes horizontales
        for (int row = 0; row <= ROWS; row++) {

            int y = row * CELL_SIZE;

            g2.drawLine(
                    0,
                    y,
                    COL * CELL_SIZE,
                    y
            );
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
    public void animerChemin(List<Point> chemin) {

        // Le premier point correspond déjà à la position de départ
        final int[] index = {1};

        Timer timer = new Timer(200, e -> {

            // Arrête l'animation lorsque le chemin est terminé
            if (index[0] >= chemin.size()) {

                ((Timer) e.getSource()).stop();
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

        timer.start();
    }

    // Anime le déplacement du joueur sur le chemin final
    public void animerRecherche(
            List<Point> noeudsExplores,
            List<Point> chemin,
            ResultatRecherche resultat,
            FenetreJeu fenetre
    ) {

        exploredNodes.clear();
        path.clear();

        final int[] index = {0};

        Timer timer = new Timer(50, e -> {

            if (index[0] >= noeudsExplores.size()) {

                ((Timer) e.getSource()).stop();

                // Affiche maintenant le chemin final
                setPath(chemin);

                // Affiche les statistiques seulement après la recherche
                fenetre.setAlgorithme("Dijkstra");
                fenetre.setNoeudsExplores(
                        resultat.getNoeudsExplores()
                );
                fenetre.setLongueurChemin(
                        resultat.getLongueurChemin()
                );
                fenetre.setCout(
                        resultat.getCout()
                );
                fenetre.setTempsExecution(
                        resultat.getTempsExecutionMs()
                );

                // Déplace ensuite le joueur
                animerChemin(chemin);

                return;
            }

            exploredNodes.add(
                    new Point(noeudsExplores.get(index[0]))
            );

            repaint();

            index[0]++;
        });

        timer.start();
    }

    // Redessine le panneau
    public void actualiser() {

        repaint();
    }
}
