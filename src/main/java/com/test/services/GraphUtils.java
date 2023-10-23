package com.test.services;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.metrics.GraphMetrics;

import java.util.Arrays;
import java.util.Stack;

public class GraphUtils {

    private static void dfs(int[][] m, int start, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int i = 0; i < m.length; i++) {
                if (m[current][i] == 1 && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static int[][] textToGraph(String graph) {
        String[] lines = graph.split("\n");
        int nr = -1;

        for (String line : lines) {
            if (line.startsWith("p edge")) {
                String[] words = line.split("[ ]");
                nr = Integer.parseInt(words[2].trim());
                break;
            }
        }

        int[][] matrix = new int[nr][nr];
        for (int[] row : matrix) {
            Arrays.fill(row, 0);
        }

        for (String line : lines) {
            if (!line.startsWith("e ")) continue;

            String[] words = line.split(" ");
            int u = Integer.parseInt(words[1].trim()) - 1;
            int v = Integer.parseInt(words[2].trim()) - 1;
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        return matrix;
    }

    public static int getVerticesNum(int[][] m) {
        return m.length;
    }

    public static int getEdgesNum(int[][] m) {
        int edges = 0;
        int n = m.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges += m[i][j] != 0 ? 1 : 0;
            }
        }
        return edges;
    }

    public static int getConnectedCompNum(int[][] m) {
        int n = m.length;
        boolean[] visited = new boolean[n];
        int componentsNr = 0;

        for (int i = 0; i < n; i++) {
            if(visited[i]) continue;

            dfs(m, i, visited);
            componentsNr++;
        }

        return componentsNr;
    }

    public static int getMinDegree(int[][] m) {
        int minDegree = -1;

        for(int[] row : m) {
            int degree = 0;
            for(int x : row) degree += x == 1 ? 1 : 0;

            if(degree < minDegree || minDegree == -1) {
                minDegree = degree;
            }
        }

        return minDegree;
    }

    public static int getMaxDegree(int[][] m) {
        int maxDegree = 0;

        for(int[] row : m) {
            int degree = 0;
            for(int x : row) degree += x == 1 ? 1 : 0;

            if(degree > maxDegree) {
                maxDegree = degree;
            }
        }

        return maxDegree;
    }

    public static int getAvgDegree(int[][] m) {
        int sum = 0;

        for(int[] row : m) {
            int degree = 0;
            for(int x : row) degree += x == 1 ? 1 : 0;

            sum += degree;
        }

        return sum / m.length;
    }

    public static int getDiameter(int[][] m) {
        int n = m.length;
        Graph graph = GraphBuilder.numVertices(n).buildDigraph();
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(m[i][j] == 0) continue;
                graph.addEdge(i, j);
                graph.addEdge(j, i);
            }
        }

//        System.out.println("diameter:");
//        var a = graph.adjacencyMatrix();
//        for(int i = 0; i < a.length; i++) {
//            System.out.println(Arrays.toString(a[i]));
//        }

        GraphMetrics metrics = new GraphMetrics(graph);
        return (int) metrics.pseudoDiameter();
    }

    public static int getRadius(int[][] m) {
        int n = m.length;
        Graph graph = GraphBuilder.numVertices(n).buildDigraph();
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                graph.addEdge(i, j);
            }
        }

        GraphMetrics metrics = new GraphMetrics(graph);
        return (int) metrics.radius();
    }
}
