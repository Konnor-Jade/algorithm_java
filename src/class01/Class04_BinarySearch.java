package class01;

import java.util.Arrays;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/17 22:55
 * @desciption: 二分查找法, 时间复杂度 O(logN)
 */
public class Class04_BinarySearch {
    public static boolean exits(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        // L~R
        while (L <= R) { // 这个范围上至少有两个数
            // mid = (L+R)/2;
            mid = L + ((R - L) >> 1);
            // mid = L + (R-L) / 2;
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                // 更新 R
                R = mid - 1;
            } else {
                // 更新 L
                L = mid + 1;
            }
        }
//        return sortedArr[L] == num;
        return  false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
//        return Arrays.asList(sortedArr).contains(num);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exits(arr, value)) {
                System.out.println(arr);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck!");
    }
}
