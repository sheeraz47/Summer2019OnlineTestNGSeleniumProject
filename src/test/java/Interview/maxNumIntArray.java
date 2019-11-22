package Interview;

import java.util.Arrays;

public class maxNumIntArray {

    public static int maxValue(int[]n){

        Arrays.sort(n);
        return n[n.length-1];
    }

    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4, 5,};
        System.out.println(maxValue(arr));

    }
}
