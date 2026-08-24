/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author udm
 */
import java.util.ArrayList;
import java.util.List;


public class Graph {
    private int numVertices;
    private List<List<Edge>> adjList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new ArrayList<>();
        
        for (int i = 0; i < numVertices; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public List<List<Edge>> getAdjList() {
        return adjList;
    }

    public void setAdjList(List<List<Edge>> adjList) {
        this.adjList = adjList;
    }

    public List<Edge> getNeighbors(int vertex) {
        return adjList.get(vertex);
    }

    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // Undirected graph
    }
}
