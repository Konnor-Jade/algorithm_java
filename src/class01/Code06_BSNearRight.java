package class01;

import java.util.Arrays;

import static class01.Code05_BSNearLeft.generateRandomArray;


/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/18 20:27
 * @desciption: 在数祖上, 找到满足<= target 最右的位置
 */
public class Code06_BSNearRight {
    public static int nearestIndex(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int L = 0, R = sortedArr.length - 1;
        int mid = 0, index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] <= target) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10, maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int target = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            Arrays.sort(arr);
            if (test(arr, target) != nearestIndex(arr, target)) {
                System.out.println(Arrays.toString(arr));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucked!");
    }

    private static int test(int[] arr, int target) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                return i;
            }
        }
        return -1;
    }
}
