import java.util.*;

public class Main {
    public static void main(String[] args) {
        testKahns();
        //testDijkstra();
        //testBFT();
        //BFTIterLinkedList(createLinkedList(10000));
    }
    public static void testKahns() {
        DirectedGraph graph=new DirectedGraph();
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addDirectedEdge(graph.getNode("0"), graph.getNode("1"));
        graph.addDirectedEdge(graph.getNode("0"), graph.getNode("2"));
        graph.addDirectedEdge(graph.getNode("0"), graph.getNode("3"));
        graph.addDirectedEdge(graph.getNode("0"), graph.getNode("4"));
        graph.addDirectedEdge(graph.getNode("2"), graph.getNode("1"));
        graph.addDirectedEdge(graph.getNode("2"), graph.getNode("3"));
        ArrayList<DirectedGraph.Node> sorted=TopSort.Kahns(graph);
        Iterator<WeightedGraph.Node> iter=sorted.iterator();
        while(iter.hasNext()) {
            DirectedGraph.Node node=iter.next();
            System.out.println("Node: "+node.value);
        }
    }
    public static void testDijkstra() {
        WeightedGraph graph=new WeightedGraph();
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("1"),9);
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("2"),6);
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("3"),5);
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("4"),3);
        graph.addWeightedEdge(graph.getNode("2"), graph.getNode("1"),2);
        graph.addWeightedEdge(graph.getNode("2"), graph.getNode("3"),4);
        HashMap<WeightedGraph.Node,Integer> dijkstraMap=TreadmillMazeSolver.dijkstras(graph.getNode("0"));
        Iterator<WeightedGraph.Node> iter=dijkstraMap.keySet().iterator();
        while(iter.hasNext()) {
            WeightedGraph.Node node=iter.next();
            System.out.println("Node: "+node.value+" Cost: "+dijkstraMap.get(node));
        }
    }
    public static void testDFS() {
        Graph linkedList=createLinkedList(10);
        HashSet<Graph.Node> nodes=linkedList.getAllNodes();
        Iterator<Graph.Node> iter=nodes.iterator();
        Graph.Node start=iter.next();
        Graph.Node end=start;
        while(iter.hasNext())
            end=iter.next();
        iter=GraphSearch.DFSRec(start,end).iterator();
        while(iter.hasNext())
            System.out.println(iter.next().value);
    }
    public static void testBFT() {
        Graph linkedList=createRandomUnweightedGraphIter(10);
        Iterator<Graph.Node> iter;
        iter=GraphSearch.BFTRec(linkedList).iterator();
        while(iter.hasNext())
            System.out.println(iter.next().value);
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
                if(done.contains(second))
                    continue;
                if(rand.nextDouble()>.5)
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
                graph.addWeightedEdge(first,iter2.next(),rand.nextInt(50));
        }
        return graph;
    }
    public static WeightedGraph createLinkedList2(final int n) {
        WeightedGraph graph=new WeightedGraph();
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
}