import java.util.HashSet;
import java.util.Vector;

public class GridGraph extends AbstractGraph {
    public class GridNode extends Node {
        private int x;
        private int y;
        public GridNode(int x,int y,String val) {
            super(val);
            this.x=x;
            this.y=y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public String toString() {
            return "X: " + x + " Y: " + y;
        }
    }

    private HashSet<GridNode> nodes;
    private Vector<Vector<GridNode>> grid;
    public GridGraph() {
        grid=new Vector<Vector<GridNode>>();
        nodes=new HashSet<>();
    }
    public void addGridNode(final int x, final int y, final String nodeVal) {
        GridNode toInsert=new GridNode(x,y,nodeVal);
        if(grid.size()<=x)
            grid.setSize(x+1);
        if(grid.get(x)==null)
            grid.set(x,new Vector<>());
        if(grid.get(x).size()<=y)
            grid.get(x).setSize(y+1);
        grid.get(x).set(y,toInsert);
        nodes.add(toInsert);
    }
    public GridNode getNode(int x, int y) {
        return grid.get(x).get(y);
    }
    public GridNode getTopRightNode() {
        return grid.get(grid.size()-1).get(grid.get(grid.size()-1).size()-1);
    }
    public HashSet<GridNode> getAllNodes() {
        return nodes;
    }
}
