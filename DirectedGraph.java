public class DirectedGraph extends AbstractDirectedGraph {
    void addDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        if(first!=second)
            first.adjacencyList.put(second,1);
    }
}