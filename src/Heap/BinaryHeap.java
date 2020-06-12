package Heap;

public class BinaryHeap<T extends Comparable<? super T>> {
    private final static int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T [] arrays;//使用数组来实现
    private int length;
    BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }
    BinaryHeap(int capacity){
        this.capacity = capacity;
        arrays = (T[]) new Comparable[(capacity+1)];//第一个数组的索引不存储元素
    }

    BinaryHeap(T [] items){
        this.length = items.length;

        arrays = (T[]) new Comparable[(length+2)*11/10];

        int i = 1;
        for(T t: items){
            arrays[i++] = t;
        }
        buildHeap();
    }
    /*
    插入元素
    * */
    public  void insert(T t){
        //如果堆满了先扩容
        if(length == arrays.length-1){
            enlargeArray(arrays.length*2 -1);
        }
        int hole = ++length;
        for(arrays[0] = t ; t.compareTo(arrays[hole/2])<0;hole/=2 ){
            //插入元素为上滤，循环的条件为 子元素小于父节点。
            //插入第一个元素的时候，数组的索引为向下取整，所以等于元素本生，循环一次，直接赋值给数组的索引++
            arrays[hole] = arrays[hole/2];

        }
        arrays[hole] = t;


    }
    /*
    清空堆
    * */
    public void clear(){
        this.length = 0 ;
    }

    /*
    找到最小的元素
    * */
    public T findMin(){
        return arrays[1];
    }

    /*
    * 判断堆是否为空*/
    public boolean isEmpty(){
        return length==0;
    }
    /*
    * 删除最小的元素*/
    public T deleteMin(){
        T tmp = arrays[1];
        /*将最后一个元素赋值给数组的最后一个元素，然后向下冒泡排序
        * */
        arrays[1]  =  arrays[length--];
        percolateDown(1);
        return tmp;
    }
    /*
    * 上滤*/
    private void percolateDown(int hole){
        int child;
        T tmp = arrays[hole];
        for(;hole*2<length;hole=child){
            //for循环的条件很关键
            child = hole*2;
            if(child!=length && arrays[child+1].compareTo(arrays[child])<0){
                child++;
            }
            if(arrays[child].compareTo(tmp)<0){
                arrays[hole] = arrays[child];
            }else {
                break;
            }
        }

        arrays[hole] = tmp;
    }

    private void buildHeap(){

    }
    /*扩大堆的长度
    * */
    private void enlargeArray(int size){
        T [] oldArr = arrays;
        arrays = (T[]) new Comparable[size];
        for(int i =0;i<oldArr.length;i++){
            arrays[i] = oldArr[i];
        }
    }


    public static void main(String[] args) {
        int items =1000;
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for(int i =37;i!=0;i = (i+37)%items){
            heap.insert(i);
        }
        for (int i = 1; i < items; i++)
            if (heap.deleteMin() != i)
                System.out.println("Oops! " + i);
    }



}
