package class01;

import java.util.Arrays;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/16 23:24
 * @desciption: 左程云算法体系班第一节，选择排序
 */
public class Code01_SelectionSort {
    // 复杂度: O(n^2)
    public static void selectionSort(int[] arr) {
        // 1️⃣ N个数过一遍，找出最小值
        // 2️⃣ 最小值放在0 位置
        // 3️⃣ 1 - N-1 范围上重复 1️⃣、2️⃣
        // 4️⃣ 周而复始
        if (arr == null || arr.length < 2) {
            return;
        }
        // 每次循环找出一个最小的数, 放在第一位
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { // i ~ N - 1找最小值
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10, maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            selectionSort(arr1);
            Arrays.sort(arr2);
            if (!Arrays.equals(arr1, arr2)) {
                succeed = false;
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("arr1 = " + Arrays.toString(arr1));
                System.out.println("arr2 = " + Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck!");
        int[] arr = generateRandomArray(maxSize, maxValue);
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxSize * Math.random());
        }
        return arr;
    }
}
