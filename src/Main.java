/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author udm
 */
import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main {
    
    public static Graph loadGraphFromDatabase(String url, String user, String pass, int totalNodes) throws Exception {
        Graph graph = new Graph(totalNodes);
        
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT source_id, destination_id, cost FROM routes");

        while (rs.next()) {
            int src = rs.getInt("source_id");
            int dest = rs.getInt("destination_id");
            int cost = rs.getInt("cost");

            graph.addEdge(src, dest, cost);
        }

        return graph;
    }

    public static void main(String[] args) throws Exception {
        Labyrinthe labyrinthe = new Labyrinthe();
        MoteurJeu moteurJeu = new MoteurJeu(labyrinthe);
        
        boolean[][] grid = moteurJeu.getLabyrinthe().getGrille();
        int cols = grid[0].length;

        Graph graphFromMaze = GraphToLabyrinthe.convertToGraph(grid);

        int startNode = 0;
        DijkstraAlgorithm solver = new DijkstraAlgorithm();
        List<Integer> nodePath = solver.findShortestPath(graphFromMaze, startNode);

        List<int[]> mazePath = GraphToLabyrinthe.convertToMazePath(nodePath, cols);

        System.out.println("Path solved! Total steps: " + mazePath.size());

        SwingUtilities.invokeLater(() -> {
            FenetreJeu fenetre = new FenetreJeu(moteurJeu);
            fenetre.setVisible(true);
        });
    }
}