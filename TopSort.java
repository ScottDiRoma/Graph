import java.util.*;

public class TopSort {
    public static ArrayList<DirectedGraph.Node> Kahns(final DirectedGraph graph) {
        ArrayList<DirectedGraph.Node> output=new ArrayList<>();
        Queue<DirectedGraph.Node> queue=new LinkedList<>();
        HashSet<DirectedGraph.Node> nodes=graph.getAllNodes();
        HashMap<DirectedGraph.Node,Integer> inDegrees=new HashMap<>();
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
        HashSet<DirectedGraph.Node> nodes=graph.getAllNodes();
        ArrayList<DirectedGraph.Node> output=new ArrayList<>(nodes.size());
        HashSet<DirectedGraph.Node> visited=new HashSet<>();
        HashSet<DirectedGraph.Node> addedToOutput=new HashSet<>();
        Stack<DirectedGraph.Node> DFTstack=new Stack<>();
        Iterator<DirectedGraph.Node> nodeIter=nodes.iterator();
        while(nodeIter.hasNext()) {
            DFTstack.push(nodeIter.next());
            while(!DFTstack.empty()) {
                DirectedGraph.Node curr=DFTstack.pop();
                if(visited.contains(curr)) {
                    if(!addedToOutput.contains(curr)) {
                        Iterator<DirectedGraph.Node> edgeIter=curr.adjacencyList.keySet().iterator();
                        while(edgeIter.hasNext())
                            if(!addedToOutput.contains(edgeIter.next()))
                                return null;
                        addedToOutput.add(curr);
                        output.add(curr);
                    }
                    continue;
                }
                DFTstack.push(curr);
                visited.add(curr);
                Iterator<DirectedGraph.Node> edgeIter=curr.adjacencyList.keySet().iterator();
                while(edgeIter.hasNext())
                    DFTstack.push(edgeIter.next());
            }
        }
        Collections.reverse(output);
        return output;
    }
}