package org.example;

import java.util.List;

public class Graph {
    String id;
    List<String> nodes;
    List<Edge> edges;

    public Graph(String id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getId() { return id; }
    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }
}


