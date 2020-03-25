import java.util.*;

public class Graph {
    public class Node {
        public LinkedHashSet<Node> adjacencyList;
        private String value;
        private Node(String v) {
            adjacencyList=new LinkedHashSet<>();
            value=v;
        }
        public String getValue() {
            return value;
        }
    }

    private HashSet<Node> nodes;
    public Graph() {
        nodes=new LinkedHashSet<Node>();
    }
    public void addNode(final String nodeVal) {
        nodes.add(new Node(nodeVal));
    }
    public void addUndirectedEdge(final Node first,final Node second) {
        if(first!=second) {
            first.adjacencyList.add(second);
            second.adjacencyList.add(first);
        }
    }
    public void removeUndirectedEdge(final Node first,final Node second) {
        first.adjacencyList.remove(second);
        second.adjacencyList.remove(first);
    }
    public HashSet<Node> getAllNodes() {
        return nodes;
    }
}