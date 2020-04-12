public abstract class AbstractDirectedGraph extends AbstractShapelessGraph {
    void removeDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        first.adjacencyList.remove(second);
    }
}
//1. Bro, just put this into another function, creating another file for something as small as this is much too cumbersome.
