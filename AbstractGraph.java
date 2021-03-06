import java.util.*;

public abstract class AbstractGraph {
    public class Node {
        public LinkedHashMap<Node,Integer> adjacencyList;
        public String value;
        protected Node(String val) {
            adjacencyList=new LinkedHashMap<>();
            value=val;
        }
        public String toString() {
            return "Node: "+value;
        }
    }

    /**
     *
     * @param first one of the nodes to be connected
     * @param second the other node to be connected
     * does nothing if first==second
     */
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
}