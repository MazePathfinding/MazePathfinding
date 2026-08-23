package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import modele.Grille;

/**
 *
 * @author fenit
 */
public class PanneauJeu extends JPanel {

    // Taille d'une case du labyrinthe en pixels
    private final int CELL_SIZE = 30;

    // Grille contenant les données du labyrinthe
    private Grille grille = new Grille();

    // Dimensions récupérées depuis la classe Grille
    private final int ROWS = grille.getRows();
    private final int COL = grille.getCols();

    // Tableau du labyrinthe : 0 = chemin libre, 1 = mur
    private int[][] maze = grille.getMaze();

    // Position du départ et de l'arrivée
    private Point start = grille.getStart();
    private Point goal = grille.getGoal();

    // Position actuelle du personnage
    private Point player = grille.getStart();

    // Chemin trouvé plus tard par un algorithme
    private List<Point> path = new ArrayList<>();

    public PanneauJeu() {

        // Calcul de la taille du panneau
        int width = COL * CELL_SIZE;
        int height = ROWS * CELL_SIZE;

        this.setPreferredSize(
                new java.awt.Dimension(width, height)
        );

        this.setBackground(Color.WHITE);
    }

    // Méthode utilisée par Swing pour dessiner le panneau
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Dessin des différents éléments du jeu
        drawMaze(g2);
        drawStartAndGoal(g2);
        drawPath(g2);
        drawPlayer(g2);
        drawGrid(g2);
    }

    // Dessine les murs du labyrinthe
    private void drawMaze(Graphics2D g2) {

        for (int row = 0; row < ROWS; row++) {

            for (int col = 0; col < COL; col++) {

                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Une case contenant 1 représente un mur
                if (maze[row][col] == 1) {

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

    // Dessine le point de départ et le point d'arrivée
    private void drawStartAndGoal(Graphics2D g2) {

        // Position du départ
        int startX = start.x * CELL_SIZE;
        int startY = start.y * CELL_SIZE;

        g2.setColor(Color.GREEN);

        g2.fillRect(
                startX,
                startY,
                CELL_SIZE,
                CELL_SIZE
        );

        // Position de l'arrivée
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

    // Dessine le chemin trouvé par l'algorithme
    private void drawPath(Graphics2D g2) {

        g2.setColor(Color.YELLOW);

        for (Point p : path) {

            int x = p.x * CELL_SIZE;
            int y = p.y * CELL_SIZE;

            // Le chemin est dessiné légèrement plus petit que la case
            g2.fillRect(
                    x + 5,
                    y + 5,
                    CELL_SIZE - 10,
                    CELL_SIZE - 10
            );
        }
    }

    // Dessine le personnage
    private void drawPlayer(Graphics2D g2) {

        int x = player.x * CELL_SIZE;
        int y = player.y * CELL_SIZE;

        g2.setColor(Color.BLUE);

        // Le personnage est représenté par un cercle
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

    // Reçoit et affiche le chemin calculé par le backend
    public void setPath(List<Point> newPath) {

        this.path = newPath;

        // Redessine le panneau
        repaint();
    }

    // Met à jour la position du personnage
    public void setPlayer(Point newPosition) {

        this.player = newPosition;

        // Redessine le panneau
        repaint();
    }
}
