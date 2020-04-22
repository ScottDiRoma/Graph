import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing on user created graphs:\n");
        TestCases.testTopSort(TestCases.createDirectedGraph());
        TestCases.testDijkstra(TestCases.createWeightedGraph());
        TestCases.testAstar(TestCases.createGridGraph());
        Graph graph;
        TestCases.testDFSandBFT(graph= TestCases.createGraph(),graph.getNode("0"),graph.getNode("4"));

        System.out.println("Testing on random graphs and linked lists:\n");
        TestCases.testTopSort(createRandomDagIter(1000));
        TestCases.testDijkstra(createRandomCompleteWeightedGraph(10));
        TestCases.testDijkstra(createWeightedLinkedList(10));
        TestCases.testAstar(createRandomGridGraph(100));
        TestCases.testDFSandBFT(graph=createRandomUnweightedGraphIter(10),graph.getNode("0"),graph.getNode("9"));
        TestCases.testDFSandBFT(graph=createLinkedList(10),graph.getNode("0"),graph.getNode("9"));
    }

    public static Graph createRandomUnweightedGraphIter(int n) {
        Graph graph=new Graph();
        for(int i=0;i<n;i++) {
            graph.addNode(""+i);
        }
        HashSet<Graph.Node> nodes=graph.getAllNodes();
        Graph.Node first,second;
        Random rand=new Random();
        for(Iterator<Graph.Node> iter1 = nodes.iterator(); iter1.hasNext();) {
            first=iter1.next();
            for(Iterator<Graph.Node> iter2 = nodes.iterator(); iter2.hasNext();) {
                second=iter2.next();
                if(rand.nextDouble()>.707)
                    graph.addUndirectedEdge(first,second);
            }
        }
        return graph;
    }
    public static Graph createLinkedList(int n) {
        Graph graph=new Graph();
        for(int i=0;i<n;i++) {
            graph.addNode(""+i);
        }
        HashSet<Graph.Node> nodes = graph.getAllNodes();
        Iterator<Graph.Node> iter = nodes.iterator();
        for(Graph.Node first,second = iter.next(); iter.hasNext();) {
            first=second;
            second=iter.next();
            graph.addUndirectedEdge(first,second);
        }
        return graph;
    }
    public static ArrayList<Graph.Node> BFTRecLinkedList(final Graph graph) {
        return GraphSearch.BFTRec(graph);
    }
    public static ArrayList<Graph.Node> BFTIterLinkedList(final Graph graph) {
        return GraphSearch.BFTIter(graph);
    }
    public static DirectedGraph createRandomDagIter(final int n) {
        DirectedGraph graph=new DirectedGraph();
        for(int i=0;i<n;i++)
            graph.addNode(""+i);
        HashSet<DirectedGraph.Node> nodes=graph.getAllNodes();
        DirectedGraph.Node first,second;
        Random rand=new Random();
        HashSet<DirectedGraph.Node> done=new HashSet<>();
        for(Iterator<DirectedGraph.Node> iter1 = nodes.iterator(); iter1.hasNext();) {
            first=iter1.next();
            done.add(first);
            for(Iterator<DirectedGraph.Node> iter2 = nodes.iterator(); iter2.hasNext();) {
                second=iter2.next();
                if(!done.contains(second)&&rand.nextDouble()>.5)
                    graph.addDirectedEdge(first,second);
            }
        }
        return graph;
    }
    public static WeightedGraph createRandomCompleteWeightedGraph(final int n) {
        WeightedGraph graph=new WeightedGraph();
        for(int i=0;i<n;i++)
            graph.addNode(""+i);
        HashSet<WeightedGraph.Node> nodes=graph.getAllNodes();
        WeightedGraph.Node first;
        Random rand=new Random();
        for(Iterator<WeightedGraph.Node> iter1 = nodes.iterator(); iter1.hasNext();) {
            first=iter1.next();
            for(Iterator<WeightedGraph.Node> iter2 = nodes.iterator(); iter2.hasNext();)
                graph.addWeightedEdge(first,iter2.next(),rand.nextInt(49)+1);
        }
        return graph;
    }
    public static WeightedGraph createWeightedLinkedList(final int n) {
        WeightedGraph graph = new WeightedGraph();
        for(int i=0;i<n;i++) {
            graph.addNode(""+i);
        }
        HashSet<WeightedGraph.Node> nodes = graph.getAllNodes();
        Iterator<WeightedGraph.Node> iter = nodes.iterator();
        for(WeightedGraph.Node first,second = iter.next(); iter.hasNext();) {
            first=second;
            second=iter.next();
            graph.addWeightedEdge(first,second,1);
        }
        return graph;
    }
    public static GridGraph createRandomGridGraph(int n) {
        GridGraph graph=new GridGraph();
        Random random=new Random();
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                graph.addGridNode(i,j,"x: "+i+"y: "+j);
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) {
                if(random.nextDouble() < .5 && j!=n-1)
                    graph.addUndirectedEdge(graph.getNode(i, j), graph.getNode(i,j+1));
                if(random.nextDouble() < .5 && i!=n-1)
                    graph.addUndirectedEdge(graph.getNode(i,j),graph.getNode(i+1,j));
            }
        return graph;
    }
}

//9. Lengthy and hard to read, needs spaces.
//10. Try to make this with portability in mind.
