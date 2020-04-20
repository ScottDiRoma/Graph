public abstract class AbstractDirectedGraph extends AbstractShapelessGraph {
    public void removeDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        first.adjacencyList.remove(second);
    }
    protected void addWeightedEdge(final WeightedGraph.Node first, final WeightedGraph.Node second,final int edgeWeight) {
        if(edgeWeight<0)
            throw new Error("edge weight can't be negative");
        if(first!=second)
            first.adjacencyList.put(second,edgeWeight);
    }
}