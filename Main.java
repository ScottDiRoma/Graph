import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing on user created graphs:\n");
        testTopSort(createDirectedGraph());
        testDijkstra(createWeightedGraph());
        testAstar(createGridGraph());
        Graph graph;
        testDFSandBFT(graph=createGraph(),graph.getNode("0"),graph.getNode("4"));

        System.out.println("Testing on random graphs and linked lists:\n");
        testTopSort(createRandomDagIter(1000));
        testDijkstra(createRandomCompleteWeightedGraph(10));
        testDijkstra(createLinkedList2(10));
        testAstar(createRandomGridGraph(100));
        testDFSandBFT(graph=createRandomUnweightedGraphIter(10),graph.getNode("0"),graph.getNode("9"));
        testDFSandBFT(graph=createLinkedList(10),graph.getNode("0"),graph.getNode("9"));
    }
    public static Graph createGraph() {
        Graph graph=new Graph();
        graph.addNode("0");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addUndirectedEdge(graph.getNode("0"), graph.getNode("1"));
        graph.addUndirectedEdge(graph.getNode("0"), graph.getNode("2"));
        graph.addUndirectedEdge(graph.getNode("0"), graph.getNode("3"));
        graph.addUndirectedEdge(graph.getNode("0"), graph.getNode("4"));
        graph.addUndirectedEdge(graph.getNode("2"), graph.getNode("1"));
        graph.addUndirectedEdge(graph.getNode("2"), graph.getNode("3"));
        return graph;
    }
    public static WeightedGraph createWeightedGraph() {
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
        return graph;
    }
    public static GridGraph createGridGraph() {
        GridGraph graph=new GridGraph();
        graph.addGridNode(0,0,"0");
        graph.addGridNode(0,1,"1");
        graph.addGridNode(0,2,"2");
        graph.addGridNode(1,0,"3");
        graph.addGridNode(1,1,"4");
        graph.addGridNode(1,2,"5");
        graph.addGridNode(2,0,"6");
        graph.addGridNode(2,1,"7");
        graph.addGridNode(2,2,"8");
        graph.addUndirectedEdge(graph.getNode(0,0), graph.getNode(0,1));
        graph.addUndirectedEdge(graph.getNode(0,0), graph.getNode(1,0));
        graph.addUndirectedEdge(graph.getNode(0,1), graph.getNode(0,2));
        graph.addUndirectedEdge(graph.getNode(0,2), graph.getNode(1,2));
        graph.addUndirectedEdge(graph.getNode(1,2), graph.getNode(2,2));
        return graph;
    }
    public static DirectedGraph createDirectedGraph() {
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
        return graph;
    }
    public static void testTopSort(DirectedGraph graph) {
        ArrayList<DirectedGraph.Node> topSort=TopSort.Kahns(graph);
        System.out.println("Testing Kahns and mDFS on a DirectedGraph");
        System.out.println("Kahns output:");
        if(topSort!=null) {
            Iterator<WeightedGraph.Node> iter = topSort.iterator();
            while (iter.hasNext()) {
                DirectedGraph.Node node = iter.next();
                System.out.println("Node: " + node.value);
            }
        }else
            System.out.println("not a DAG");
        topSort=TopSort.mDFS(graph);
        System.out.println("mDFS output:");
        if(topSort!=null) {
            Iterator<WeightedGraph.Node> iter = topSort.iterator();
            while (iter.hasNext()) {
                DirectedGraph.Node node = iter.next();
                System.out.println("Node: " + node.value);
            }
        }else
            System.out.println("not a DAG");
        System.out.println();
    }
    public static void testAstar(GridGraph graph) {
        System.out.println("Testing Astar and Dijkstra on a GridGraph");
        System.out.println("Dijkstras map:");
        HashMap<AbstractGraph.Node,Integer> dijkstraMap=TreadmillMazeSolver.dijkstras(graph.getNode(0,0));
        Iterator<AbstractGraph.Node> iter=dijkstraMap.keySet().iterator();
        while(iter.hasNext()) {
            AbstractGraph.Node node=iter.next();
            System.out.println(node+" Cost: "+dijkstraMap.get(node));
        }
        System.out.println("Astar path:");
        ArrayList<GridGraph.GridNode> arr=TreadmillMazeSolver.astar(graph.getNode(0,0),graph.getTopRightNode());
        if(arr!=null) {
            Iterator<GridGraph.GridNode> iter2 = arr.iterator();
            while (iter2.hasNext())
                System.out.println(iter2.next());
        }else {
            System.out.println("No path exists");
        }
        System.out.println();
    }
    public static void testDijkstra(WeightedGraph graph) {
        AbstractGraph.Node source=graph.getAllNodes().iterator().next();
        HashMap<AbstractGraph.Node,Integer> dijkstraMap=TreadmillMazeSolver.dijkstras(source);
        Iterator<AbstractGraph.Node> iter=dijkstraMap.keySet().iterator();
        System.out.println("Testing Dijkstra on a WeightedGraph");
        System.out.println("Source: "+source);
        while(iter.hasNext()) {
            AbstractGraph.Node node=iter.next();
            System.out.println(node+" Cost: "+dijkstraMap.get(node));
        }
        System.out.println();
    }
    public static void testDFSandBFT(Graph graph, Graph.Node source,Graph.Node dest) {
        System.out.println("Testing DFSRec, DFSIter, BFTRec, and BFTIter");
        System.out.println("DFSRec:");
        ArrayList<Graph.Node> arr=GraphSearch.DFSRec(graph.getNode("0"),graph.getNode("4"));
        Iterator<Graph.Node> iter;
        if(arr!=null) {
            iter = arr.iterator();
            while (iter.hasNext())
                System.out.println(iter.next());
        }else {
            System.out.println("No path exists");
        }
        System.out.println("DFSIter:");
        arr=GraphSearch.DFSIter(graph.getNode("0"),graph.getNode("4"));
        if(arr!=null) {
            iter = arr.iterator();
            while (iter.hasNext())
                System.out.println(iter.next());
        }else {
            System.out.println("No path exists");
        }
        System.out.println("BFTRec:");
        arr=GraphSearch.BFTRec(graph);
        iter = arr.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
        System.out.println("BFTIter:");
        arr=GraphSearch.BFTIter(graph);
        iter = arr.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
        System.out.println();
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
                graph.addWeightedEdge(first,iter2.next(),rand.nextInt(49)+1);
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
    public static GridGraph createRandomGridGraph(int n) {
        GridGraph graph=new GridGraph();
        Random random=new Random();
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                graph.addGridNode(i,j,"x: "+i+"y: "+j);
        for(int i=0;i<n;i++)
            for(int j=0;j<n-1;j++)
                if(random.nextDouble()<.5)
                    graph.addUndirectedEdge(graph.getNode(i,j),graph.getNode(i,j+1));
        for(int i=0;i<n-1;i++)
            for(int j=0;j<n;j++)
                if(random.nextDouble()<.5)
                    graph.addUndirectedEdge(graph.getNode(i,j),graph.getNode(i+1,j));
        return graph;
    }
}