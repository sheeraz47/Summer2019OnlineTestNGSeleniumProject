package Interview;

public class SawapNumber {

    public static void main(String[] args) {

        // swap number int value with out using third variable

            int a = 10; int b = 20;

            a = a + b; //==>30
            // a = 10 + 20 = 30
            b = a - b; // 10
        //  b = 30 - 20
            a = a - b; // 20
        // 30 - 20 ==> 10
        System.out.println("a is : "+ a +""+" b is : "+ b);
    }
}
