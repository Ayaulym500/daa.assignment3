package org.example;

import java.io.*;
import java.util.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        try {
            String inputText = new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get("input.json")));
            JSONObject jsonInput = new JSONObject(inputText);
            JSONArray graphsArray = jsonInput.getJSONArray("graphs");

            JSONArray results = new JSONArray();

            for (int i = 0; i < graphsArray.length(); i++) {
                JSONObject g = graphsArray.getJSONObject(i);
                String id = g.getString("id");


                JSONArray nodesArr = g.getJSONArray("nodes");
                List<String> nodes = new ArrayList<>();
                for (int j = 0; j < nodesArr.length(); j++)
                    nodes.add(nodesArr.getString(j));


                JSONArray edgesArr = g.getJSONArray("edges");
                List<Edge> edges = new ArrayList<>();
                for (int j = 0; j < edgesArr.length(); j++) {
                    JSONObject e = edgesArr.getJSONObject(j);
                    edges.add(new Edge(e.getString("from"), e.getString("to"), e.getInt("weight")));
                }

                Graph graph = new Graph(id, nodes, edges);


                MSTAlgorithms.Result primResult = MSTAlgorithms.prim(graph);
                MSTAlgorithms.Result kruskalResult = MSTAlgorithms.kruskal(graph);


                System.out.println("\nGraph ID: " + id);
                System.out.println("Vertices: " + nodes.size() + ", Edges: " + edges.size());
                System.out.println("Prim cost = " + primResult.totalCost +
                        ", edges = " + primResult.mstEdges.size() +
                        ", ops = " + primResult.operations +
                        ", time = " + primResult.timeMs + " ms");
                System.out.println("Kruskal cost = " + kruskalResult.totalCost +
                        ", edges = " + kruskalResult.mstEdges.size() +
                        ", ops = " + kruskalResult.operations +
                        ", time = " + kruskalResult.timeMs + " ms");
                System.out.println("Costs equal? " +
                        (primResult.totalCost == kruskalResult.totalCost));


                JSONObject result = new JSONObject();
                result.put("graph_id", id);
                result.put("vertices", nodes.size());
                result.put("edges", edges.size());

                JSONObject primJSON = new JSONObject();
                primJSON.put("total_cost", primResult.totalCost);
                primJSON.put("operations", primResult.operations);
                primJSON.put("time_ms", primResult.timeMs);
                primJSON.put("mst_edges", primResult.mstEdges.toString());

                JSONObject kruskalJSON = new JSONObject();
                kruskalJSON.put("total_cost", kruskalResult.totalCost);
                kruskalJSON.put("operations", kruskalResult.operations);
                kruskalJSON.put("time_ms", kruskalResult.timeMs);
                kruskalJSON.put("mst_edges", kruskalResult.mstEdges.toString());

                result.put("prim", primJSON);
                result.put("kruskal", kruskalJSON);
                results.put(result);
            }


            JSONObject output = new JSONObject();
            output.put("results", results);

            try (FileWriter fw = new FileWriter("output.json")) {
                fw.write(output.toString(2)); // с отступами
            }

            System.out.println("\nResults saved to output.json successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

