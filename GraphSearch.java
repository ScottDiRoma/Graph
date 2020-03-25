import java.util.*;

public class GraphSearch {
    public static ArrayList<Graph.Node> DFSRec(final Graph.Node start, final Graph.Node end) {
        ArrayList<Graph.Node> searchOrder=new ArrayList<Graph.Node>();
        Set<Graph.Node> explored=new HashSet<Graph.Node>();
        DFSRecHelper(start,end,explored,searchOrder);
        if(explored.contains(end))
            return searchOrder;
        else
            return null;
    }
    private static boolean DFSRecHelper(final Graph.Node start, final Graph.Node end, Set<Graph.Node> explored,ArrayList<Graph.Node> searchOrder) {
        if(explored.contains(start))
            return false;
        else
            explored.add(start);
        searchOrder.add(start);
        if(start==end)
            return true;
        Stack<Graph.Node> stack=new Stack<Graph.Node>();
        for(Graph.Node node:start.adjacencyList) {
            stack.push(node);
        }
        boolean done=false;
        while(!stack.empty()&&!done)
            done=DFSRecHelper(stack.pop(),end,explored,searchOrder);
        return false;
    }
    public static ArrayList<Graph.Node> DFSIter(final Graph.Node start, final Graph.Node end) {
        ArrayList<Graph.Node> searchOrder=new ArrayList<Graph.Node>();
        Set<Graph.Node> explored=new HashSet<Graph.Node>();
        Stack<Graph.Node> stack = new Stack<Graph.Node>();
        stack.push(start);
        while(!stack.empty()) {
            Graph.Node curr=stack.pop();
            if (explored.contains(curr))
                continue;
            else
                explored.add(curr);
            searchOrder.add(curr);
            if(curr==end) break;
            for (Graph.Node node : curr.adjacencyList) {
                stack.push(node);
            }
        }
        if(explored.contains(end))
            return searchOrder;
        else
            return null;
    }
    public static ArrayList<Graph.Node> BFTRec(final Graph graph) {
        ArrayList<Graph.Node> searchOrder=new ArrayList<Graph.Node>();
        Set<Graph.Node> explored=new HashSet<Graph.Node>();
        LinkedList<Graph.Node> queue = new LinkedList<Graph.Node>();
        HashSet<Graph.Node> nodes=graph.getAllNodes();
        Iterator<Graph.Node> iter=nodes.iterator();
        while(explored.size()!=nodes.size()&&iter.hasNext())
            BFTRecHelper(iter.next(), queue, explored, searchOrder);
        return searchOrder;
    }
    public static void BFTRecHelper(Graph.Node node,Queue<Graph.Node> queue,Set<Graph.Node> explored,List<Graph.Node> searchOrder) {
        if(explored.contains(node)) {
            if(queue.peek()!=null)
                BFTRecHelper(queue.remove(),queue,explored,searchOrder);
            return;
        }
        explored.add(node);
        searchOrder.add(node);
        for (Graph.Node n : node.adjacencyList)
            queue.add(n);
        if(queue.peek()!=null)
            BFTRecHelper(queue.remove(),queue,explored,searchOrder);
    }
    public static ArrayList<Graph.Node> BFTIter(final Graph graph) {
        ArrayList<Graph.Node> searchOrder=new ArrayList<Graph.Node>();
        Set<Graph.Node> explored=new HashSet<Graph.Node>();
        Queue<Graph.Node> queue = new LinkedList<Graph.Node>();
        HashSet<Graph.Node> nodes=graph.getAllNodes();
        Iterator<Graph.Node> iter=nodes.iterator();
        while(explored.size()!=nodes.size()&&iter.hasNext()) {
            queue.add(iter.next());
            while(queue.peek() != null) {
                Graph.Node curr = queue.remove();
                if (explored.contains(curr))
                    continue;
                else
                    explored.add(curr);
                searchOrder.add(curr);
                for (Graph.Node node : curr.adjacencyList) {
                    queue.add(node);
                }
            }
        }
        return searchOrder;
    }
}