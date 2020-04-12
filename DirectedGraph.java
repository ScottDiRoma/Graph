public class DirectedGraph extends AbstractDirectedGraph {
    void addDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        if(first!=second)
            first.adjacencyList.put(second,1);
    }
}
//5. So many files it's making me dizzy.
