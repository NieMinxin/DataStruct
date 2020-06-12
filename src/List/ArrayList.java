package List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ArrayList<AnyType> implements Iterable {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private AnyType [] items;

    ArrayList(){

    }
    public void security(int index){

        if(index<0||index>size()){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void trimToSize(){
        extendCapacity(size());
    }

    //判断是否为空
    public boolean isEmpty(){
        return size()==0;
    }
    //清除
    public void clear(){
        for(int i=0;i<size;i++){
            items[i]=null;
        }

        size=0;
    }
    public void init(){
        size=0;
        extendCapacity(DEFAULT_CAPACITY);
    }

    //扩容
    public void  extendCapacity (int newCapcacity){
        if(newCapcacity<size){
            return;
        }
        AnyType [] old = items;
        items = (AnyType[]) new Object[newCapcacity];
        for(int i=0;i<size();i++){
            items[i]=old[i];
        }
    }
    //返回表的长度
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<size;i++){
            builder.append(items[i]);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        return "["+builder.toString()+"]";
    }

    /*===============================================================*/
    //没找到返回-1
    public int indexOf(AnyType o){
        if(o!=null){
            for(int i=0;i<size;i++){
                if(o.equals(items[i])){
                    return  i;
                }
            }
        }
        return -1;
    }

    //获取指定的元素
    public AnyType get(int index){
       security(index);
        return items[index];
    }

    //替换指定索引的值
    public AnyType set(int index,AnyType value){
       security(index);
       AnyType old = items[index];
       items[index] = value;
       return old;
    }

    //追加元素
    public boolean add(AnyType value){
        add(size(),value);
        return true;
    }
    //添加元素
    private void add(int index, AnyType value) {
        security(index);
        if(items.length==size()){
            extendCapacity(size()*2+1);

        }
        for(int i=size;i>index;i--){
            items[i] = items[i-1];//向后移动数组
        }
        items[index] = value;//插入新值
        size++;
    }

    public  AnyType remove(int index){
        security(index);
        AnyType del = items[index];
        for(int i=index;i<size()-1;i++){
            items[i] = items[i+1];
        }
        size--;
        return del;
    }


    @Override
    public Iterator iterator() {
        return new Itr();
    }
    //迭代器遍历
    private class Itr implements Iterator{
        private int current=0;

        @Override
        public boolean hasNext() {
            return current<size();
        }

        @Override
        public Object next() {
            if(!hasNext()){
                throw  new NoSuchElementException();
            }
            return items[current++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(--current);
        }


    }
}
