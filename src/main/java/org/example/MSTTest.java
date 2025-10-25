package org.example;

import java.util.*;

public class MSTTest {
    public static void main(String[] args) {


        testSmallGraph();
        testMediumGraph();
        testLargeGraph();
        testDisconnectedGraph();

        System.out.println("\nAll tests completed.");
    }

    // Small Graph
    private static void testSmallGraph() {
        System.out.println("\n Small Graph Test ");
        Graph graph = new Graph(
                "Small",
                Arrays.asList("A", "B", "C", "D"),
                Arrays.asList(
                        new Edge("A", "B", 1),
                        new Edge("A", "C", 4),
                        new Edge("B", "C", 2),
                        new Edge("C", "D", 3),
                        new Edge("B", "D", 5)
                )
        );
        runTest(graph);
    }

    //  Medium Graph
    private static void testMediumGraph() {
        System.out.println("\n Medium Graph Test");
        Graph graph = generateGraph("Medium", 12, 0.4);
        runTest(graph);
    }

    // Large Graph
    private static void testLargeGraph() {
        System.out.println("\n Large Graph Test");
        Graph graph = generateGraph("Large", 30, 0.3);
        runTest(graph);
    }

    //  Disconnected Graph
    private static void testDisconnectedGraph() {
        System.out.println("\n Disconnected Graph Test");
        Graph graph = new Graph(
                "Disconnected",
                Arrays.asList("A", "B", "C"),
                Arrays.asList(
                        new Edge("A", "B", 5)
                        // C изолирована
                )
        );
        runTest(graph);
    }


    private static void runTest(Graph graph) {
        MSTAlgorithms.Result prim = MSTAlgorithms.prim(graph);
        MSTAlgorithms.Result kruskal = MSTAlgorithms.kruskal(graph);

        System.out.println("Vertices: " + graph.getNodes().size() +
                ", Edges: " + graph.getEdges().size());
        System.out.println("Prim cost=" + prim.totalCost + ", ops=" + prim.operations +
                ", time=" + prim.timeMs + " ms");
        System.out.println("Kruskal cost=" + kruskal.totalCost + ", ops=" + kruskal.operations +
                ", time=" + kruskal.timeMs + " ms");

        check("Equal total cost", prim.totalCost == kruskal.totalCost);
        check("Prim edges = V-1", prim.mstEdges.size() == graph.getNodes().size() - 1);
        check("Kruskal edges = V-1", kruskal.mstEdges.size() == graph.getNodes().size() - 1);
        check("Prim connected and acyclic", isAcyclicAndConnected(graph.getNodes(), prim.mstEdges));
        check("Kruskal connected and acyclic", isAcyclicAndConnected(graph.getNodes(), kruskal.mstEdges));
        check("Execution time non-negative", prim.timeMs >= 0 && kruskal.timeMs >= 0);
        check("Operation count non-negative", prim.operations >= 0 && kruskal.operations >= 0);
    }

    private static boolean isAcyclicAndConnected(List<String> nodes, List<Edge> edges) {
        if (edges.isEmpty()) return false;
        Map<String, String> parent = new HashMap<>();
        for (String n : nodes) parent.put(n, n);
        for (Edge e : edges) {
            String r1 = find(parent, e.from);
            String r2 = find(parent, e.to);
            if (r1.equals(r2)) return false;
            parent.put(r1, r2);
        }
        Set<String> roots = new HashSet<>();
        for (String n : nodes) roots.add(find(parent, n));
        return roots.size() == 1;
    }

    private static String find(Map<String, String> parent, String v) {
        if (!parent.get(v).equals(v)) parent.put(v, find(parent, parent.get(v)));
        return parent.get(v);
    }

    private static void check(String text, boolean condition) {
        System.out.println(" - " + text + ": " + (condition ? "PASSED" : "FAILED"));
    }

    private static Graph generateGraph(String id, int vertices, double density) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < vertices; i++) nodes.add("V" + i);

        List<Edge> edges = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                if (rnd.nextDouble() < density) {
                    edges.add(new Edge("V" + i, "V" + j, rnd.nextInt(90) + 10));
                }
            }
        }
        return new Graph(id, nodes, edges);
    }
}




