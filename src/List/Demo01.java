package List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
public class Demo01  {
    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());


        }
        iterator.remove();//删除


        System.out.println(collection);
    }


}
