package List;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Demo02 {
    public static void main(String[] args) {
        Collection<Integer> collection = new LinkedList();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);
        collection.add(5);
        collection.add(6);

        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()){
            if((iterator.next())%2==0){
                iterator.remove();
            }
        }
        System.out.println(collection);
    }
}
