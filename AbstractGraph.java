import java.util.*;

public abstract class AbstractGraph {
    public class Node {
        public LinkedHashMap<Node,Integer> adjacencyList;
        public String value;
        private Node(String v) {
            adjacencyList=new LinkedHashMap<Node,Integer>();
            value=v;
        }
    }

    private HashSet<Node> nodes;
    public AbstractGraph() {
        nodes=new LinkedHashSet<Node>();
    }
    public void addNode(final String nodeVal) {
        nodes.add(new Node(nodeVal));
    }
    public void addUndirectedEdge(final Node first,final Node second) {
        if(first!=second) {
            first.adjacencyList.put(second,1);
            second.adjacencyList.put(first,1);
        }
    }
    public void removeUndirectedEdge(final Node first,final Node second) {
        first.adjacencyList.remove(second);
        second.adjacencyList.remove(first);
    }
    public HashSet<Node> getAllNodes() {
        return nodes;
    }
    public Node getNode(String val) {
        Iterator<Node> iter=nodes.iterator();
        while(iter.hasNext()) {
            Node next=iter.next();
            if(next.value.equals(val))
                return next;
        }
        return null;
    }
}