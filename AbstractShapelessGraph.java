import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class AbstractShapelessGraph extends AbstractGraph {
    private HashSet<Node> nodes;
    public AbstractShapelessGraph() {
        nodes=new LinkedHashSet<Node>();
    }
    public void addNode(final String nodeVal) {
        nodes.add(new Node(nodeVal));
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
//3. I'd rather this just be in a larger file containing all graph abstractions. 
