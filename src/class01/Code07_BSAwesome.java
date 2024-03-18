package class01;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/18 20:44
 * @desciption: 无序情况下的二分法应用:查找局部最小
 */
public class Code07_BSAwesome {
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 判断 0 位置是不是局部最小
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }

        // 判断 N-1 位置是不是局部最小
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        // 在 1~(N-2)位置上查找局部最小
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
