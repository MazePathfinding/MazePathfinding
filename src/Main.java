/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author udm
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main {

    // Reads records directly from the database 
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
        String dbUrl = "https...chaimae"; 
        String user = "username:chaimea";
        String pass = "password";

        int totalVertices = 5;

        Graph graph = loadGraphFromDatabase(dbUrl, user, pass, totalVertices);

        //run Dijkstra
        int sourceNode = 0;
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        List<Integer> distances = dijkstra.findShortestPath(graph, sourceNode);

        System.out.println("Shortest distances from source node " + sourceNode + ":");
        for (int i = 0; i < distances.size(); i++) {
            System.out.println("Node " + i + " -> Distance: " + distances.get(i));
        }
    }
}