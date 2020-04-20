import java.io.UncheckedIOException;

public class WeightedGraph extends AbstractDirectedGraph {
    public void addWeightedEdge(final WeightedGraph.Node first, final WeightedGraph.Node second,final int edgeWeight) {
        super.addWeightedEdge(first, second, edgeWeight);
    }
}