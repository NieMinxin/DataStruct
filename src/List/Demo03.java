package List;

public class Demo03 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.init();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.remove(1);
        System.out.println(arrayList.size());
        System.out.println(arrayList);
        for(Object i:arrayList){
            System.out.println(i);
        }


    }
}
