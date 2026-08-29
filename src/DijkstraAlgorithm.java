/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author udm
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    public List<Integer> findShortestPath(Graph graph, int source) {
        int v = graph.getNumVertices();
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // PriorityQueue ordered by distance
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>((a, b) -> a.getDistance() - b.getDistance());

        dist[source] = 0;
        pq.add(new NodeDistance(source, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            int u = current.getNode();
            int currentDist = current.getDistance();

            if (currentDist > dist[u]) {
                continue;
            }

            for (Edge edge : graph.getNeighbors(u)) {
                int neighbor = edge.getDestination();
                int weight = edge.getWeight();

                if (dist[u] + weight < dist[neighbor]) {
                    dist[neighbor] = dist[u] + weight;
                    pq.add(new NodeDistance(neighbor, dist[neighbor]));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int d : dist) {
            result.add(d);
        }
        return result;
    }
}
