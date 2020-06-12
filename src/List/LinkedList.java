package List;

import org.omg.CORBA.Any;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<AnyType> implements Iterable<AnyType>{
    private Node<AnyType>  header;
    private Node<AnyType> tail;
    int size;
    int modCount=0;
    @Override
    public Iterator iterator() {
        return new ListItr();

    }
    //迭代器模式
    private class ListItr implements  Iterator{
        private Node<AnyType> current = header.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current!=tail;
        }

        @Override
        public Object next() {
            if(modCount!=expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw  new NoSuchElementException();
            }
            okToRemove=true;
            current=current.next;
            return current.data;
        }

        @Override
        public void remove() {
            if(modCount!=expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!okToRemove){
                throw  new IllegalStateException();
            }
            LinkedList.this.unLink(current.prev);//删除前置节点
            expectedModCount++;
            okToRemove=false;
        }

    }
    //节点
    private static class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> prev;
        private Node<AnyType> next;
        public Node(Node<AnyType> prev,AnyType data,Node<AnyType> next){
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    //-=================================================基本方法

    //构造器初始化
    LinkedList(){
        size = 0;
        header = new Node<>(null,null,null);
        tail = new Node<>(header,null,null);
        header.next = tail;
        modCount++;
    }

    //获取头节点
    public AnyType getFirst(){
        final Node f= header;
        if(f==null){
            throw new NoSuchElementException();
        }
        return (AnyType) f.data;
    }
    //获取未节点
    public AnyType getLast() {
        final Node<AnyType> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return l.data;
    }

    //获取链表数据的长度
    public  int size(){
        return size;
    }

    //判断链表是否为空
    public boolean isEpmty(){
        return size==0;
    }

    //清楚
    public void  clear(){
        for (Node<AnyType> x = header; x != null; ) {
            Node<AnyType> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        header = tail = null;
        size = 0;
        modCount++;
    }
    //判断索引值是否符合常理
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    //检查
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException();
    }
    //索引安全检查
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException();
    }

    //获取具体索引的NODE
    /*
    用于循环时获取具体索引的节点 ，这里采用二分的方法，优化查找结点的速度
    * */
    Node<AnyType> node( int index){
        Node<AnyType>  temp;
        if(index<(size>>1)){
            temp = header.next;
            for(int i=0;i<index;i++){
                temp = temp.next;
            }
        }else {
            temp =tail;
            for(int i=size;i>index;i--){
                temp = temp.prev;
            }
        }
        return temp;
    }
    //获取指定索引的节点的值
    public AnyType get(int index){
        checkElementIndex(index);//安全检查
        return node(index).data;
    }
    //修改指定索引的值,并返回旧值
    public AnyType set(int index,AnyType newValue){
        Node<AnyType> p = node(index);
        AnyType old = p.data;
        p.data = newValue;
        return old;
    }
    public void add(int index,AnyType value){
        addBefore(node(index),value);
    }
    public boolean add(AnyType v){
        add(size,v);
        return true;
    }

    //print
    public void print(){
        Node<AnyType> temp = header;
        for(int i=0;i<size;i++){
            temp=temp.next;
            System.out.println(temp.data);
        }
    }

    private void addBefore(Node<AnyType> node,AnyType value){

        Node<AnyType> newNode = new Node<>(node.prev,value,node);
        newNode.prev.next=newNode;
        node.prev = newNode;
        size++;
        modCount++;
    }

    public AnyType remove(int index){
        return unLink(node(index));
    }
    //删除
    private AnyType unLink(Node<AnyType> node){
        final AnyType item = node.data;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
        modCount--;
        return  node.data;
    }

}
