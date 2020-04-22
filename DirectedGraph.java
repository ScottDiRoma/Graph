public class DirectedGraph extends AbstractDirectedGraph {
    void addDirectedEdge(final AbstractDirectedGraph.Node first, final AbstractDirectedGraph.Node second) {
        super.addWeightedEdge(first,second,1);
    }
}
//5. So many files it's making me dizzy.
