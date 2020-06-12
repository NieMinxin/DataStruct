package Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class BfsGraph extends DfsGraph {

    public BfsGraph(int vertex) {
        super(vertex);
    }

    public void bfs(int v){
        boolean [] visited = new boolean[vertex];

        LinkedList<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);
        while (!queue.isEmpty()){
            v = queue.poll();
            System.out.println(v+"===");
            Iterator<Integer> iterator = adj[v].listIterator();
            while(iterator.hasNext()){
                int n = iterator.next();
                if(!visited[n]){
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        BfsGraph g = new BfsGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 2)");

        g.bfs(2);
    }
}
