import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TestCases {
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
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("1"), 9);
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("2"), 6);
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("3"), 5);
        graph.addWeightedEdge(graph.getNode("0"), graph.getNode("4"), 3);
        graph.addWeightedEdge(graph.getNode("2"), graph.getNode("1"), 2);
        graph.addWeightedEdge(graph.getNode("2"), graph.getNode("3"), 4);
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

    public static void testDFSandBFT(Graph graph, Graph.Node source, Graph.Node dest) {
        System.out.println("Testing DFSRec, DFSIter, BFTRec, and BFTIter");
        Iterator<Graph.Node> iter;
        System.out.println("DFSIter:");
        ArrayList<Graph.Node> arr=GraphSearch.DFSIter(graph.getNode("0"),graph.getNode("4"));
        if(arr!=null) {
            iter = arr.iterator();
            while (iter.hasNext())
                System.out.println(iter.next());
        }else {
            System.out.println("No path exists");
        }
        System.out.println("BFTIter:");
        arr=GraphSearch.BFTIter(graph);
        iter = arr.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
        System.out.println("BFTRec:");
        arr=GraphSearch.BFTRec(graph);
        iter = arr.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
        System.out.println("DFSRec:");
        arr=GraphSearch.DFSRec(graph.getNode("0"),graph.getNode("4"));
        if(arr!=null) {
            iter = arr.iterator();
            while (iter.hasNext())
                System.out.println(iter.next());
        }else {
            System.out.println("No path exists");
        }
        System.out.println();
    }
}
