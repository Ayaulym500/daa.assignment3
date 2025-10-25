REPORT
1. 
The input data was read from a JSON file (input.json) describing a transportation network.
Each dataset included a list of vertices (cities) and weighted edges (roads with distances or costs).


Both algorithms produced a Minimum Spanning Tree (MST) with identical total cost.

![Graph Illustration](https://github.com/Ayaulym500/daa.assignment3/blob/master/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202025-10-25%20113357.png)


Both algorithms produced the same total MST cost = 10.

Operation count includes key steps like comparisons, edge selections, and union/find operations.

The final results were  stored in an output.json file

2. Comparison: Prim’s and Kruskal’s Algorithm
![Graph Illustration](https://github.com/Ayaulym500/daa.assignment3/blob/master/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202025-10-25%20113453.png)


Practical Comparison (Based on Results)

Execution Time:
Kruskal’s algorithm executed faster (1.32 ms vs 2.06 ms) on the small graph.

Prim’s algorithm required more time due to repeated key updates in the priority queue.



Operations:
Kruskal’s performed only 5 operations, while Prim’s required 15, showing Kruskal’s better efficiency for smaller graphs.



Scalability:
For larger graphs, the same pattern continues — Kruskal’s remains faster and lighter in operations for sparse networks,

while Prim’s may perform better on dense graphs with optimized heap implementation.

3. Conclusions

Both algorithms correctly produced the same MST with identical total cost, confirming algorithmic consistency.

However, their performance differs depending on graph structure:

    Prim’s Algorithm is preferable when:

•	The graph is dense (many edges per vertex);

•	An adjacency matrix is used;

•	You need to grow the MST dynamically (e.g., in network expansion problems).

   Kruskal’s Algorithm is preferable when:

•	The graph is sparse;

•	The input is naturally represented as an edge list;

•	You want simpler parallelization or easier implementation with Union-Find.

In this project:

•	Kruskal’s algorithm demonstrated slightly better performance (lower runtime and fewer operations).

•	Prim’s algorithm was still efficient and robust, but required more comparisons during heap updates.

Therefore, Kruskal’s algorithm is more efficient for this dataset, but the optimal choice depends on the graph’s density and data representation.

