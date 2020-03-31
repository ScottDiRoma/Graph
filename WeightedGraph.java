public class WeightedGraph extends AbstractDirectedGraph {
    void addWeightedEdge(final WeightedGraph.Node first, final WeightedGraph.Node second,final int edgeWeight) {
        if(first!=second)
            first.adjacencyList.put(second,edgeWeight);
    }
}