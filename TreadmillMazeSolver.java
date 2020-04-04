import java.util.*;

public class TreadmillMazeSolver {
    public static HashMap<AbstractGraph.Node,Integer> dijkstras(final AbstractGraph.Node start) {
        HashMap<WeightedGraph.Node,Integer> visited=new HashMap<>();
        HashMap<WeightedGraph.Node,Integer> working=new HashMap<>();
        PriorityQueue<SearchNode> pq=new PriorityQueue<>();
        SearchNode curr;
        pq.add(new SearchNode(start,0));
        while((curr=pq.poll())!=null) {
            if(visited.containsKey(curr.node))
                continue;
            visited.put(curr.node,curr.cost);
            Iterator<WeightedGraph.Node> iter=curr.node.adjacencyList.keySet().iterator();
            while(iter.hasNext()) {
                WeightedGraph.Node node=iter.next();
                Integer newDistance=curr.cost+curr.node.adjacencyList.get(node);
                if(!working.containsKey(node)||newDistance<working.get(node)) {
                    working.put(node,newDistance);
                    pq.add(new SearchNode(node,newDistance));
                }
            }
        }
        return visited;
    }

    /**
     * @return optimal path from sourceNode to destNode
     */
    public static ArrayList<GridGraph.GridNode> astar(final GridGraph.GridNode sourceNode, final GridGraph.GridNode destNode) {
        HashSet<WeightedGraph.Node> visited=new HashSet<>();
        PriorityQueue<SearchNode> pq=new PriorityQueue<>();
        pq.add(new SearchNode(sourceNode,0,manhattan(sourceNode,destNode),null));
        while(!pq.isEmpty()) {
            SearchNode curr=pq.remove();
            if(visited.contains(curr.node))
                continue;
            visited.add(curr.node);
            if(curr.node==destNode) {                                  //if at destNode, trace backwards and build the output array, then return
                ArrayList<GridGraph.GridNode> output=new ArrayList<>();
                Stack<GridGraph.GridNode> outputStack=new Stack<>();
                while(curr!=null) {
                    outputStack.push((GridGraph.GridNode) curr.node);
                    curr=curr.parent;
                }
                while(!outputStack.empty())
                    output.add(outputStack.pop());
                return output;
            }
            Iterator<AbstractGraph.Node> edgeIter=curr.node.adjacencyList.keySet().iterator();
            while(edgeIter.hasNext()) {
                GridGraph.GridNode edgeNode=(GridGraph.GridNode) edgeIter.next();
                pq.add(new SearchNode(edgeNode,curr.cost+1,manhattan(edgeNode,destNode),curr));
            }
        }
        return null;
    }

    /**
     *  manhattan distance heuristic function
     */
    private static Integer manhattan(GridGraph.GridNode sourceNode,GridGraph.GridNode destNode) {
        return Math.abs(sourceNode.getX()-destNode.getX())+Math.abs(sourceNode.getY()-destNode.getY());
    }

    /**
     * Represents a node in an A* or Dijkstra search
     * implements Comparable so that it can be used in priority queue
     */
    private static class SearchNode implements Comparable<SearchNode> {
        private AbstractGraph.Node node;
        private Integer cost;             //cost to get to this node
        private Integer heuristic;        //heuristic function of this node
        private SearchNode parent;        //the previous node
        public SearchNode(AbstractGraph.Node n,Integer c) {
            node=n;
            cost=c;
            heuristic=0;
        }
        public SearchNode(AbstractGraph.Node n,Integer c,Integer h,SearchNode p) {
            node=n;
            cost=c;
            heuristic=h;
            parent=p;
        }
        public int compareTo(SearchNode node2) {
            if(cost+heuristic<node2.cost+node2.heuristic)
                return -1;
            if(cost+heuristic>node2.cost+node2.heuristic)
                return 1;
            return 0;
        }
    }
}