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

public class GraphToLabyrinthe {

    public static Graph convertToGraph(boolean[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int totalVertices = rows * cols;

        Graph graph = new Graph(totalVertices);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c]) continue; // Skip walls (true = mur)

                int currentId = r * cols + c;
                int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !grid[nr][nc]) {
                        int neighborId = nr * cols + nc;
                        graph.addEdge(currentId, neighborId, 1);
                    }
                }
            }
        }
        return graph;
    }
 
    public static List<int[]> convertToMazePath(List<Integer> nodePath, int cols) {
        List<int[]> mazePath = new ArrayList<>();

        if (nodePath == null) return mazePath;

        for (int nodeId : nodePath) {
            int r = nodeId / cols; // Row formula
            int c = nodeId % cols; // Column formula
            mazePath.add(new int[]{r, c});
        }

        return mazePath;
    }
}