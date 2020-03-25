import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testBFT();
        BFTIterLinkedList(createLinkedList(10000));
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
            System.out.println(iter.next().getValue());
    }
    public static void testBFT() {
        Graph linkedList=createRandomUnweightedGraphIter(10);
        Iterator<Graph.Node> iter;
        iter=GraphSearch.BFTRec(linkedList).iterator();
        while(iter.hasNext())
            System.out.println(iter.next().getValue());
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
}