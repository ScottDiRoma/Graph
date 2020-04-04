public abstract class AbstractDirectedGraph extends AbstractShapelessGraph {
    void removeDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        first.adjacencyList.remove(second);
    }
}