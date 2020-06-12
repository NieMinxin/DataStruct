package Algorithm;

public class Gcd {
    //欧几里得算法
    public static  int gcd(long m,long n){
        while(n!=0){
            long temp = m%n;
            m=n;
            n=temp;
        }
        return (int) m;
    }
}
