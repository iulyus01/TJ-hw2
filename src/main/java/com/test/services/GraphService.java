package com.test.services;

import com.test.domain.Output;

import java.util.Arrays;

public class GraphService {

    private static GraphService graphService;

    private GraphService() {}

    public static GraphService getInstance() {
        if(graphService == null) graphService = new GraphService();
        return graphService;
    }

    public Output handleTextGraph(String text) {
        int[][] m = GraphUtils.textToGraph(text);
        for (int[] row : m) {
            System.out.println(Arrays.toString(row));
        }

        Output output = new Output();
        output.setVerticesNum(GraphUtils.getVerticesNum(m));
        output.setEdgesNum(GraphUtils.getEdgesNum(m));
        output.setConnectedCompNum(GraphUtils.getConnectedCompNum(m));
        output.setMinDegree(GraphUtils.getMinDegree(m));
        output.setMaxDegree(GraphUtils.getMaxDegree(m));
        output.setAvgDegree(GraphUtils.getAvgDegree(m));
        output.setDiameter(GraphUtils.getDiameter(m));
        output.setRadius(GraphUtils.getRadius(m));

        return output;
    }
}
