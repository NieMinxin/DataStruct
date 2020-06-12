package Hashing;

import java.util.LinkedList;
import java.util.List;

public class HashTable<T> {
    //哈希表容量
    private  static  final int DEFAULT_SIZE = 101;

    //数组
    private List<T>[] table;

    //当前大小
    private int currentSize;

    //散列函数
    private int hash(T t){
        int hash = t.hashCode();
        hash = hash % table.length;
        if(hash<0){
            hash = hash + table.length;
        }
        return hash;
    }

    //重新hash
    private void rehash(){}

    //无参构造器默认容量
    public HashTable(){
        this(DEFAULT_SIZE);
    }
    // 初始化数组的容量
    public HashTable(int size){
        table = new LinkedList[size];//链表数组
        for(int i=0;i<table.length;i++){
            table[i] = new LinkedList<>();
        }
    }

    public void inser(T t){
        List<T> list = table[hash(t)];
        if(!contain(t)){
            list.add(t);
            currentSize++;
        }

    }

    public void remove(T t){
        List<T> list = table[hash(t)];//链表数组
        if(contain(t)){
            list.remove(t);
            currentSize--;
        }
    }

    public boolean contain(T t){

        List<T> list = table[hash(t)];
        return list.contains(t);
    }
    //清空
    public void Empty(){
        for(int i = 0;i<table.length;i++){
            table[i].clear();
        }
        currentSize=0;
    }

    public static void main(String[] args) {
        HashTable table = new HashTable(10);
        table.inser(1);

    }

}
