package Algorithm;

public class Binary {
    final static  int ANSWER = 37;
    public static void main(String[] args) {
        int [] a = new int[]{1,2,3,4,5,6,7,8};
        int index = binarySearch(a,3);
        System.out.println(index);

    }

    public static int binarySearch(int [] a,int num){
        int low =0,high = a.length-1;
        while(low<=high){
            int center = (low+high)/2;
            if(a[center]<num){
                low = center+1;
            }else if(a[center]>num){
                high = center-1;
            }else {
                return center;
            }
        }
        return -1;//-1代表未找到
    }
}
