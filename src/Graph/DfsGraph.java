package Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class DfsGraph {


    protected int vertex;//顶点数

    protected LinkedList<Integer> [] adj;

    public DfsGraph(int vertex){
        this.vertex =vertex;
        adj = new LinkedList[vertex];
        for(int i =0;i<vertex;i++){
            adj[i] = new LinkedList<>();
        }

    }

    public void addEdge(int start,int end){
        adj[start].add(end);
    }

    private void dfs(int v,boolean[] visited){
        visited[v] = true;
        System.out.println(v+" ");
        Iterator<Integer> iterator = adj[v].iterator();
        while(iterator.hasNext()){
            int n = iterator.next();
            if(!visited[n]){
                dfs(n,visited);
            }
        }
    }

    public void DFS(int v){
        boolean [] visited = new boolean[vertex];
        dfs(v,visited);
    }

    public static void main(String[] args) {

        DfsGraph g = new DfsGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 2)");

        g.DFS(0);



    }



}
