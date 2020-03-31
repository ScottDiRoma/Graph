import java.util.*;

public class TopSort {
    public static ArrayList<DirectedGraph.Node> Kahns(final DirectedGraph graph) {
        ArrayList<DirectedGraph.Node> output=new ArrayList<>();
        Queue<DirectedGraph.Node> queue=new LinkedList<>();
        HashSet<DirectedGraph.Node> nodes=graph.getAllNodes();
        LinkedHashMap<DirectedGraph.Node,Integer> inDegrees=new LinkedHashMap<>();
        Iterator<DirectedGraph.Node> nodeIter=nodes.iterator();
        while(nodeIter.hasNext()) {
            DirectedGraph.Node node=nodeIter.next();
            Iterator<DirectedGraph.Node> edgeIter=node.adjacencyList.keySet().iterator();
            while(edgeIter.hasNext()) {
                DirectedGraph.Node edge=edgeIter.next();
                if(inDegrees.containsKey(edge))
                    inDegrees.put(edge,inDegrees.get(edge)+1);
                else
                    inDegrees.put(edge,1);
            }
        }
        nodeIter=nodes.iterator();
        while(nodeIter.hasNext()) {
            DirectedGraph.Node node=nodeIter.next();
            if(!inDegrees.containsKey(node)) {
                queue.add(node);
            }
        }
        while(queue.peek()!=null) {
            DirectedGraph.Node node=queue.remove();
            output.add(node);
            Iterator<DirectedGraph.Node> edgeIter=node.adjacencyList.keySet().iterator();
            while(edgeIter.hasNext()) {
                DirectedGraph.Node edge=edgeIter.next();
                inDegrees.put(edge,inDegrees.get(edge)-1);
                if(inDegrees.get(edge)==0)
                    queue.add(edge);
            }
        }
        if(output.size()!=nodes.size())
            return null;
        return output;
    }
    public static ArrayList<DirectedGraph.Node> mDFS(final DirectedGraph graph) {
        return null;
    }
}