package class02;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/19 08:30
 * @desciption: 交换两个数
 */
public class Code01_Swap {
    public static void main(String[] args) {
        int a = 16, b = 603;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        int[] arr = {3, 1, 100};
        int j = 0;
        int i = 1;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        System.out.println("arr[i] = " + arr[i]);
    }
}
