package List;

public class Stack<AnyType> {
    private final static int  DEFAULT_SIZE=10;
    private int length;
    private int top;
    private AnyType [] items;
    Stack(){
       top=-1;
       length=0;
       items = (AnyType[]) new Object[DEFAULT_SIZE];

    }
    public boolean isEmpty(){
        return top==-1;
    }
    public int length(){
        return length;
    }
    public void push(AnyType value){
        items[++top] = value;
        length++;
    }
    public AnyType pop(){
       AnyType temp = items[top];
       items[top] = null;//GC
       top--;
       length--;

       return temp;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println("是否为空---"+stack.isEmpty());
        java.util.Stack stack1 = new java.util.Stack();

    }
    
}
