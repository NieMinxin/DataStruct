package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * 邻接矩阵实现的无向图
 */
public class AWGraph {
    private ArrayList vertices = null;//顶点
    private int[][] edges =null;//边

    private boolean [] visited;

    public  AWGraph(int vertices){
        this.vertices = new ArrayList();
        this.edges = new int[vertices][vertices];

        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
                this.edges[i][j] = 0;
            }
        }
        //初始化
    }

    public void insertEdge(int v1,int v2){
        edges[v1][v2] = 1;
        edges[v2][v1] = 1;

    }
    public void print(){
        System.out.println(this.vertices);
        for(int i =0;i<this.edges.length;i++){
            System.out.println(Arrays.toString(edges[i]));
        }
    }

    //深度搜索
    private void dfs(){
        this.visited = new boolean[vertices.size()];
        for(int i=0;i<visited.length;i++){
            visited[i] = false;
        }

        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                DFS(i);
            }
        }

    }

    public  void DFS(int site){
        System.out.println(vertices.get(site));
        this.visited[site] = true;
        for(int i =0;i<this.vertices.size();i++){
            if(edges[site][i]!=0 && !this.visited[i]){
                DFS(i);
            }
        }
    }



    //广度搜索
    public void bfs(){
        this.visited = new boolean[this.vertices.size()];
        for(int i = 0; i < this.visited.length; i++) {
            this.visited[i] = false;
        }

        for(int i = 0; i < this.visited.length; i++) {
            if(!this.visited[i]) {
                BFS(i);
               }
            }
    }




    private void BFS(int site){
        System.out.println(this.vertices.get(site));
        this.visited[site] = true;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(site);

        while (!queue.isEmpty()){
            int v = queue.poll();
            for(int i=0;i<vertices.size();i++){
                if(edges[v][i]!=0 && !this.visited[i]){
                    System.out.println(vertices.get(i));
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }



    }


    public static void main(String[] args) {
        AWGraph g = new AWGraph(5);
        g.vertices.add("A");
        g.vertices.add("B");
        g.vertices.add("C");
        g.vertices.add("D");
        g.vertices.add("E");


        g.insertEdge(0,1);
        g.insertEdge(0,2);
        g.insertEdge(1,2);
        g.insertEdge(1,3);
        g.insertEdge(2,3);
        g.insertEdge(2,4);

        g.print();
        g.dfs();
        System.out.println("广度搜索");
        g.bfs();

    }

}
