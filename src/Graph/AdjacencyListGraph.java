package Graph;

import java.util.ArrayList;

public class AdjacencyListGraph <T extends Comparable<? super T>>{

    //顶点表
    ArrayList<Vertex> vertices;

    private  int edge;

    public AdjacencyListGraph(){
        vertices = new ArrayList<>();
        edge = 0;
    }
    /**
     * 定点
     */

    private class Vertex{
        T data;
        ArrayList<Vertex> adjaencyVertex;

        public Vertex(T data){
            this.data = data;
            this.adjaencyVertex = new ArrayList<>();
        }

        public boolean addAdjacentVertex(Vertex to){
            for(Vertex v:vertices){
                if((v.data.compareTo(to.data))==0){
                    return false;
                }
            }
            return adjaencyVertex.add(to); //return true
        }

        public boolean removeAdjaentVertex(T to){
            for(Vertex v:adjaencyVertex){
                if((v.data.compareTo(to))==0){
                    adjaencyVertex.remove(to);
                    return true;
                }

            }
            return false;
        }

    }

    /**
     *
     * @param from  起始顶点的值
     * @param to    邻接顶点的值
     * @return      成功添加边，返回 true ，否则false
     */
    public boolean addEdge(T from,T to){
        Vertex fromV=null,toV = null;
        for(Vertex v:vertices){
            if((from.compareTo(v.data))==0){
                fromV = v;
            }else if((to.compareTo(v.data))==0){
                toV = v;
            }
            if(fromV!=null && toV!=null){
                break;
            }

        }
        if(fromV==null){
            fromV = new Vertex(from);
            vertices.add(fromV);
        }
        if(toV==null){
            toV = new Vertex(to);
            vertices.add(toV);
        }
        this.edge++;
        return fromV.addAdjacentVertex(toV);
    }

    public boolean removeEdge(T from,T to){
        Vertex fromV = null;
        for(Vertex v:vertices){
            if(from.compareTo(v.data)==0){
                fromV = v;
            }
            break;
        }
        if(fromV == null) return false;
        edge--;
        return fromV.removeAdjaentVertex(to);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent verticies: ");
            for (Vertex v2 : v.adjaencyVertex) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
            sb.append("edge =");
            sb.append(edge);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph();
        graph.addEdge(1,2);
        graph.addEdge(1,5);
        graph.addEdge(5,3);
        graph.addEdge(2,4);
        graph.addEdge(1,3);

        System.out.println(graph);

    }
}
