package class01;

import java.util.Arrays;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/18 12:28
 * @desciption: 在数组上,找到满足>=target 的最左的位置
 */
public class Code05_BSNearLeft {
    public static int nearestIndex(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1; // 表示不存在,错误
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0, index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] >= target) {
                // 更新 R
                index = mid;
                R = mid - 1;
            } else {
                // 更新 L
                L = mid + 1;
            }
        }
        return index;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int test(int[] sortedArr, int num) {

        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] >= num) { // 这里必须填写大于等于
                return i;
            }
        }

        return -1;
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
            if (test(arr, value) != nearestIndex(arr, value)) {
                System.out.println(Arrays.toString(arr));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck!");
    }
}
