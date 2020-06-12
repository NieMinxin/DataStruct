package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private static  final  int DEFALUT_WEIGHT = -1;

    ArrayList vertices = new ArrayList();

    int [][] edges;//边

    private int num;


    public int getEdge(){
        return num;
    }


    public Graph(int n){
        this.edges = new int[n][n];
        for(int i = 0;i<n;i++){
            for(int j =0;j<n;j++){
                edges[i][j] = DEFALUT_WEIGHT;
            }
        }
        num =0;
    }

    public int getVertices(){
        return vertices.size();
    }


    public int getWeightOfEdges(int v1,int v2) throws Exception{
        if((v1 < 0 || v1 >= vertices.size())||(v2 < 0||v2 >= vertices.size()))
        {
            throw new Exception("v1或者v2参数越界错误！");
        }
        return this.edges[v1][v2];

    }

    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        this.num++;

    }
    public void deleteEdge(int v1,int v2,int weight){
        this.edges[v1][v2]=DEFALUT_WEIGHT;
        this.edges[v2][v1]=DEFALUT_WEIGHT;
        this.num--;
    }

    public void print(){
        System.out.println(this.vertices);
        for(int i =0;i<this.edges.length;i++){
            System.out.println(Arrays.toString(edges[i]));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(3);
        graph.vertices.add("a");
        graph.vertices.add("b");
        graph.vertices.add("c");
        //a-b a-c

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);

        graph.print();
    }
}
