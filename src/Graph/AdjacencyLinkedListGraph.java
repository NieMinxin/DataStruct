package Graph;

import java.util.*;

public class AdjacencyLinkedListGraph <E extends Comparable<? super E>>{

    /**
     * 节点-----顶点
     */
    private class Node<E>{
        E data;
        public Node(E data){
            this.data = data;
        }

    }

    /**
     * 边
     */
    private  class Edge{
        Node start;
        Node end;
        public Edge(Node start,Node end){
            this.start = start;
            this.end = end;
        }

    }

    private ArrayList<Node<E>> vertices;//存放顶点

    private ArrayList<Edge> edges;//存放边

    public AdjacencyLinkedListGraph(){
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

    }

    private  Node<E> root;

    /**
     *
     * @param t1  节点标志内容来找到相应的节点
     * @param t2    节点标志内容来找到相应的节点
     *              将t1 和 t2 添加边
     */
    public void addEdge(E t1,E t2){
        Node<E> start=null,end =null;
        for(Node<E> node:vertices){
            if(node.data.compareTo(t1)==0){
                start = node;
            }else if(node.data.compareTo(t2)==0){
                end=node;
            }
        }

            if (start==null){
                start = new Node<E>(t1);
                if(vertices.size()==0){
                    root = start;
                }
                vertices.add(start);

            }
            if(end==null){
                end = new Node<E>(t2);
                vertices.add(end);
            }


        edges.add(new Edge(start,end));
    }


    /**
     *   深度优先搜索 -------  中取出第一个节点，并检验它是否为目标。
     *
     * 如果找到目标，则结束搜寻并回传结果。
     *
     * 否则将它某一个尚未检验过的直接子节点加入stack中。
     *      * @param n       the actual visiting node
     *      * @param visited A list of already visited nodes in the depth first search
     *      * @return returns a set of visited nodes
     */

    public ArrayList<Node> dfs(Node<E> n,ArrayList<Node> visited){
        visited.add(n);
        System.out.println(n.data);
        for(Edge edge:edges){
            if(edge.start.equals(n) && !visited.contains(edge.end)){
                dfs(edge.end,visited);
            }
        }
        return visited;
    }





    public int countGraphs(){
        int count =0;
        Set<Node> markedNode = new HashSet<>();

        for(Node n : vertices){
            if(!markedNode.contains(n)){
                markedNode.add(n);
                markedNode.addAll(dfs(n,new ArrayList<Node>()));
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        AdjacencyLinkedListGraph<Character> graph = new AdjacencyLinkedListGraph<Character>();
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'e');
        graph.addEdge('b', 'e');
        graph.addEdge('b', 'c');
        graph.addEdge('c', 'd');
        graph.addEdge('d', 'a');

        graph.dfs(graph.root,new ArrayList<>());





        System.out.println(graph.countGraphs());
    }

}
