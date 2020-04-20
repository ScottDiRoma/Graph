public class DirectedGraph extends AbstractDirectedGraph {
    void addDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        super.addWeightedEdge(first,second,1);
    }
}