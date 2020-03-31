public abstract class AbstractDirectedGraph extends AbstractGraph {
    void addDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        if(first!=second)
            first.adjacencyList.put(second,1);
    }
    void removeDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        first.adjacencyList.remove(second);
    }
}