import java.util.*;

public class TreadmillMazeSolver {
    public static HashMap<WeightedGraph.Node,Integer> dijkstras(final AbstractGraph.Node start) {
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
    public static ArrayList<WeightedGraph.Node> astar(final WeightedGraph graph) {
        return null;
    }

    private static class SearchNode implements Comparable<SearchNode> {
        private WeightedGraph.Node node;
        private Integer cost;
        public SearchNode(WeightedGraph.Node n,Integer c) {
            node=n;
            cost=c;
        }
        public int compareTo(SearchNode node2) {
            if(cost<node2.cost)
                return -1;
            if(cost>node2.cost)
                return 1;
            return 0;
        }
    }
}