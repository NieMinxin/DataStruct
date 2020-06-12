package List;
//循环队列
public class Queue<AnyType> {
    //对头的0号索引不使用
    private int font;//头
    private int rear;//尾
    private int length;
    private final static  int DEFAULT_SIZE =10;//队列的大小

    private AnyType items[];

    Queue(){
        font=0;
        rear=0;
        length=0;
        items = (AnyType[]) new Object[DEFAULT_SIZE];
    }
    public boolean isFull(){
        return (rear+1)%DEFAULT_SIZE==font;
    }

    public boolean isEmpty(){
        return length==0;
    }

    public int getLength(){
        return length;
    }

    public void enqueue(AnyType value){
        if(!isFull()){
            rear=(rear+1)%DEFAULT_SIZE;
            items[rear] = value;
            length++;
            System.out.println("入队成功-"+" "+ value);
        }else {
            System.out.println("队列已经满了，请等待!");
        }

    }

    public AnyType dequeue(){
        font = (font+1)%DEFAULT_SIZE;
        length--;
        return items[font];
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for(int i =0;i<9;i++){
            queue.enqueue(i);
        }
        queue.enqueue(10);
        System.out.println("队列长度"+queue.getLength());
        System.out.println("出队元素：="+queue.dequeue());
        queue.enqueue(10);
        System.out.println("队列长度"+queue.getLength());
    }

}
