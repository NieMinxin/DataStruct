package Sort;

import java.util.Arrays;

public class Sort<T extends Comparable<? super T>> {


    /**
     *
     * 归并排序
     */
    private <T extends Comparable<? super T>> void  mergeSort(T[] oArr,T [] tmp,int left,int right){
        if(left<right){

            int center = (left+right)/2;
            mergeSort(oArr, tmp, left, center);
            mergeSort(oArr, tmp, center+1,right);
            merge(oArr,tmp,left,center+1,right);
        }
    }

    private <T extends Comparable<? super T>> void merge(T[] oArr,T [] tmp,int leftPos,int RightPos,int rightEnd){
        int leftEnd = RightPos -1;
        int elements = rightEnd - leftPos +1;
        int tmpPos = leftPos;
        //循环
        while(leftPos<=leftEnd && RightPos <= rightEnd){
            if((oArr[leftPos].compareTo(oArr[RightPos]))<=0){
                //比较大小，将小的元素放入tmp数组
                tmp[tmpPos++] = oArr[leftPos++];
            }else {
                tmp[tmpPos++] = oArr[RightPos++];
            }
        }
        //复制前半部分剩余的元素
        while(leftPos<=leftEnd){
            tmp[tmpPos++] = oArr[leftPos++];
        }

        //复制后半部分剩余的元素
        while(RightPos<=rightEnd){
            tmp[tmpPos++] = oArr[RightPos++];
        }
        //copy back

        for(int i =0;i<elements;i++,rightEnd--){
            oArr[rightEnd] = tmp[rightEnd];
        }
    }

    public <T extends Comparable<? super T>> void mergeSort(T[] Arr){
        T [] tmpArr = (T[]) new Comparable[Arr.length];
        mergeSort(Arr,tmpArr,0,Arr.length-1);
    }





    //插入排序
    public void insertSort(T[] ts){
        int j;
        for(int i=1;i<ts.length;i++){
            T tmp = ts[i];

            for(j=i;j>0 && (tmp.compareTo(ts[j-1])<0);j--){
                ts[j] = ts[j-1];
            }
            ts[j] = tmp;
        }

    }

    //希尔排序
    public void shellSort(T [] ts){
        int j;
        for(int num=ts.length/2;num>0;num /=2){
            for(int i=num;i<ts.length;i++){
                T tmp = ts[i];
                for(j=i;j >=num && (tmp.compareTo(ts[j-num])<0);j-=num){

                    ts[j] = ts[j-num];

                }
                ts[j] = tmp;

            }

        }
    }

    /**
     * 堆排序
     * @param
     */

    private  int leftChild(int n){
        return (n<<1) + 1;
    }

    /**
     *
     * @param ts  数组
     * @param index  下滤索引
     * @param length 堆元素数量
     * @param <T>  泛型数据类型
     */
    private <T extends Comparable<? super T>> void perDown(T [] ts,int index,int length){
        int child;
        T tmp = ts[index];

        child = leftChild(index);//左孩子

        while(child<length){
            int rChild = child+1;
            if(rChild<length && (ts[child].compareTo(ts[rChild])<0)){
                child++;
            }

            if((tmp.compareTo(ts[child]))>0){
                break;
            }else{
                ts[index] = ts[child];
            }


            index = child;
            child = leftChild(child);

            }
        ts[index] = tmp;
        }


    public  <T extends Comparable<? super T>>  void heapSort(T [] ts){
        //创建堆，通过下滤创建堆
        for(int i=ts.length/2-1;i>=0;i--){
            perDown(ts,i,ts.length);
        }

        for(int i=ts.length-1;i>0;i--){
            T tmp = ts[i];
            ts[i] = ts[0];
            ts[0] = tmp;
            //交换堆顶元素和堆尾元素

            //重新调整堆
            perDown(ts,0,i);

        }


    }


    /**
     *
     * 快速排序
     */
    private static final int CUTOFF = 3;

    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] a, int left, int right) {
        if (left == right) {
            return;
        }
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            //分割策略
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1);   // Restore pivot

            quickSort(a, left, i - 1);    // Sort small elements
            quickSort(a, i + 1, right);
        } else {
            insertionSort(a,left,right);//插入排序

        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;

            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    private static void insertionSort(int[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            int tmp = a[p];
            int j;

            for (j = p; j > left && tmp<(a[j - 1]); j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }


    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /**
     *
     * @param arr  排序的数组
     * @param bucketSize   排序数组类型的约束--泛型上界约束
     * @return  排序好的数组  Arrays.copyOf(sourceArray, sourceArray.length);
     */
    public  int[] bucketSort(int[] arr, int bucketSize){
        if(arr.length==0){
            return arr;
        }
        int minValue = arr[0];
        int maxValue = arr[0];
        //找出最大和最小的值
        for(int value:arr){
            if(value<minValue){
                minValue = value;
            }else if(value>maxValue){
                maxValue = value;
            }
        }
        int bucketCount = (int)Math.floor((maxValue - minValue)/bucketSize)+1;
        int [][] buckets = new int[bucketCount][0];

        // 利用映射函数将数据分配到各个桶中
        for(int i=0;i<arr.length;i++){
            int index = (int)Math.floor((arr[i]-minValue)/bucketSize);
            buckets[index] = append(buckets[index],arr[i]);
        }


        //对桶进行排序

        int index = 0;
        for(int i = 0;i<buckets.length;i++){
            if(buckets[i].length<=0){
                continue;
            }
            insertionSort(buckets[i],0,buckets[i].length-1);
            for(int value:buckets[i]){
                arr[index++] = value;
            }

        }



        return  arr;
    }


    /**
     * 桶自动扩容
     * @param arr 数组
     * @param value 值
     */
    private int[] append(int [] arr,int value){
        arr = Arrays.copyOf(arr,arr.length+1);
        arr[arr.length-1] = value;
        return arr;
    }


    /**基数排序
     *
     * @param
     */
    public int [] radixSort(int[] arr){
        int maxValue = getMaxValue(arr);

        return radixSort(arr,getMaxNum(maxValue));
    }


    private int [] radixSort(int [] arr,int max){
        int n =1;
        int time =1;//排序次数，由位数最多的元素决定
        int [][] tmp = new int[10][arr.length];
        int[] order = new int[10];//order 用来表示该位是i的元素的个数
        while(time<=max){
            for(int i =0;i<arr.length;i++){
                int lsd = (arr[i]/n)%10;
                tmp[lsd][order[lsd]] = arr[i];
                order[lsd]++;//元素出现次数
            }
            int k=0;
            for(int i=0;i<10;i++){
                if(order[i]!=0){
                    for(int j=0;j<order[i];j++){
                        arr[k] = tmp[i][j];
                        k++;
                    }
                    order[i] = 0;
                }

            }
            n*=10;
            time++;
        }


        return arr;
    }

    private int getMaxNum(int num){
        if(num==0){
            return 1;
        }
        int len = 0;
        for(int tmp = num;tmp!=0;tmp/=10){
            len++;
        }
        return len;
    }

    private int getMaxValue(int[] arr){
        int maxValue = arr[0];
        for(int i=0;i<arr.length;i++){
            if (maxValue<arr[i]){
                maxValue = arr[i];
            }
        }
        return maxValue;
    }
    //获取数的最高位
    private int getMaxDit(int [] arr){
        int maxV = getMaxValue(arr);
        return  getMaxNum(maxV);
    }

    /**
     *
     *  计数排序
     */



    public static void main(String[] args) {
        Integer[] a = new Integer[]{12,35,42,55,8,3,67,34,23,57};
        Sort<Integer> sort = new Sort<Integer>();
        Sort.quickSort(a);
        for(Integer i : a){
            System.out.println(i);
        }

        /*Integer [] a1 = new Integer[]{32,89,67,3,17,73,9,63,28,21};
        sort.mergeSort(a1);
        System.out.println("归并排序");
        for(Integer i: a1){
            System.out.println(i);
        }*/

        /*Integer [] a1 = new Integer[]{32,89,67,3,17,73,9,63,28,21};
        sort.heapSort(a1);
        System.out.println("堆排序");
        for(Integer i : a1){
            System.out.println(i);
        }
*/
        /*Integer [] a1 = new Integer[]{32,89,67,3,17,73,9,63,28,21};
        sort.shellSort(a1);
        System.out.println("================希尔排序");
        for(Integer i : a1){
            System.out.println(i);
        }*/
        int[] a1 = new int[]{12,35,42,55,8,3,67,34,23,57};

        System.out.println("---桶排序：");
        int [] arr = sort.bucketSort(a1,10);
        for(int value:arr){
            System.out.println(value+"\t");
        }


        System.out.println("----------基数排序");
        int [] arr1 = sort.radixSort(arr);
        for(int value:arr1){
            System.out.println(value);
        }


    }







}
