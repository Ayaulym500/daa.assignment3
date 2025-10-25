package org.example;

import java.util.*;

public class MSTAlgorithms {

    public static class Result {
        List<Edge> mstEdges;
        int totalCost;
        int operations;
        double timeMs;

        public Result(List<Edge> mstEdges, int totalCost, int operations, double timeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.timeMs = timeMs;
        }
    }

    // Крускал
    public static Result kruskal(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;

        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Edge::compareTo);

        Map<String, String> parent = new HashMap<>();
        for (String node : graph.getNodes()) parent.put(node, node);

        for (Edge edge : edges) {
            operations++;
            String root1 = find(parent, edge.from);
            String root2 = find(parent, edge.to);
            if (!root1.equals(root2)) {
                parent.put(root1, root2);
                result.add(edge);
            }
        }

        int totalCost = result.stream().mapToInt(e -> e.weight).sum();
        double timeMs = (System.nanoTime() - start) / 1_000_000.0;

        return new Result(result, totalCost, operations, timeMs);
    }

    private static String find(Map<String, String> parent, String v) {
        if (!parent.get(v).equals(v)) {
            parent.put(v, find(parent, parent.get(v)));
        }
        return parent.get(v);
    }

    //Прима
    public static Result prim(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;

        List<Edge> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());

        String startNode = graph.getNodes().get(0);
        visited.add(startNode);

        while (visited.size() < graph.getNodes().size()) {
            Edge minEdge = null;
            int minWeight = Integer.MAX_VALUE;

            for (Edge edge : edges) {
                operations++;
                boolean oneIn = visited.contains(edge.from);
                boolean otherIn = visited.contains(edge.to);
                if (oneIn ^ otherIn && edge.weight < minWeight) {
                    minWeight = edge.weight;
                    minEdge = edge;
                }
            }

            if (minEdge == null) break;
            result.add(minEdge);
            visited.add(minEdge.from);
            visited.add(minEdge.to);
        }

        int totalCost = result.stream().mapToInt(e -> e.weight).sum();
        double timeMs = (System.nanoTime() - start) / 1_000_000.0;

        return new Result(result, totalCost, operations, timeMs);
    }
}



