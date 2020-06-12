package List;

import sun.security.jgss.spnego.SpNegoCredElement;

public class Demo04 {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(0,0);
        linkedList.remove(4);
        System.out.println(linkedList.size);
        linkedList.print();
    }
}
