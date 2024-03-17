package class01;

import java.util.Arrays;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/17 10:28
 * @desciption: 快速排序, 快排, O(n ^ 2) + 对数器
 */
public class Class03_InsertionSort {
    // 从小到大排序
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0-0 上有序
        // 0-1 上有序
        // 0-2 上有序
        // ...
        // 0-(N-1) 上有序
        for (int i = 1; i < arr.length ; i++) { // 0~i 做到有序
            // 下面的条件中,前置序列已经有序,所以只要交换到不满足前一个不大于后一个
            // 那么本次排序借宿
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // i 和 j 不能为同一个位置,否则会 异或 为 0
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j]; // i^j
        arr[j] = arr[i] ^ arr[j]; // i^j^j=i
        arr[i] = arr[i] ^ arr[j]; // i^j^i=j
    }

    // 保证对的方法, 官方实现, 肯定对, 标准答案, 用作比对
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];// (maxSize +1) * Math.random()) -> [0,N]
        for (int i = 0; i < arr.length; i++) {
            // (maxValue + 1) * Math.random() [0,N]
            // maxValue * Math.random() [0,N-1]
            // [-(N - 1), N]之间
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        // 调用库
//        return arr.clone();
        if (arr == null) {
            return null;
        }
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100; // 随机数组的长度 0~100
        int maxValue = 100; // 数组中数据的大小 -100~100
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr); // arr.clone()
            int[] arr2 = arr.clone();
            insertionSort(arr1);
            comparator(arr2);
            if (!Arrays.equals(arr1, arr2)) {
                succeed = false;
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }

        }
        System.out.println(succeed ? "全对":"出错了");
        int[] arr = generateRandomArray(maxSize,maxValue);
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));

    }


}
