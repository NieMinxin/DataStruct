package Heap;

public class LeftistHeap<Anytype extends Comparable<? super Anytype>>{
    private  Node  root;
    /*
    节点，内部类
    * */
    private static class Node<AnyType>{
        Node<AnyType> left;//左节点
        Node<AnyType> right;//右节点
        int npl;//npl
        AnyType element;//元素内容
        Node(AnyType element){
            this(element,null,null);
        }

        Node(AnyType element,Node left,Node right){
            this.left = left;
            this.right = right;
            this.element = element;
            npl = 0;
        }

    }

    public LeftistHeap(){
        this.root = null;
    }

    /*合并算法*/
    public void merge(LeftistHeap rhs){
        if(this == rhs) return;

        root = merge(root,rhs.root);
        rhs.root = null;
    }


    public void insert(Anytype ele){
        root = merge(new Node<>(ele),root);

    }

    public  boolean isEmpty(){
        return root == null;
    }

    public Anytype findMin() throws Exception{
        if(isEmpty()){
            throw  new Exception("左式堆为空");

        }
        return (Anytype) root.element;
    }

    public Anytype deleteMin() throws Exception{
        if(isEmpty()){
            throw  new Exception("堆为空");
        }
        Anytype min = (Anytype) root.element;
        root = merge(root.left,root.right);
        return min;
    }

    public  void clear(){
        root = null;
    }


    private Node<Anytype> merge(Node<Anytype> h1,Node<Anytype> h2){
        if(h1==null){
            return h2;
        }else if(h2==null){
            return h1;
        }
        if(h1.element.compareTo(h2.element)<0){
            return mergel(h1, h2);
        }else {
            return mergel(h2,h1);
        }
    }

    private Node<Anytype> mergel(Node<Anytype> h1,Node<Anytype> h2){
        //h1 为较小值的根
        if(h1.left==null){
            h1.left = h2;//根元素
        }else {
            h1.right = merge(h1.right,h2);
            if(h1.left.npl<h1.right.npl){
                swapChildren(h1);
            }
            //修改npl长度
            h1.npl = h1.right.npl + 1;
        }

        return h1;
    }

    private static<Anytype> void swapChildren(Node<Anytype> node){
        Node<Anytype> tmp  = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public static void main(String[] args) {

    }


}
