package class02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/19 09:26
 * @desciption: 一个数组中有一种数出现K次，其他数都出现了M次， M > 1， K < M 找到，出现了K次的数，
 * 如果这个数没有出现 K 次,就返回-1
 * 要求，额外空间复杂度O（1），时间复杂度O（N）
 */
public class Code03_KMSearch {
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] times = new int[32];

        // 统计每个数的二进制第 i 位的数量,存储进 times 数组中
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                times[i] += (num >> i) & 1;
            }
        }
        // 找出times 中不是 m 倍数的位置,这些位置就是需要找到的数 target
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (times[i] % m != 0) {
                // 如果模的结果是 0
                if (times[i] % m == k) {
                    ans |= (1 << i);
                } else {
                    // 既不是 0 也不是 k,说明没有符合条件数,直接返回-1
                    return -1;
                }
            }
        }
        // 边界条件
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // hash 表统计每个数字出现的次数
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历数组,如果出现次数是k,就返回这个数
        for (int i : map.keySet()) {
            if (map.get(i) == k) {
                return i;
            }
        }
        return -1;
    }
    // 生成符合要求的数组
    /**
     * @param maxKinds: 种类数量
     * @param range:    数的范围
     * @param k:        真命天子
     * @param m:        陪跑🤡
     * @return arr: 乱序的数组
     */
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        // 一. 设置初始参数
        // 确定 k 次的数
        // 确定 k 出现的次数
        // 一共要包含多少种数
        int kTimeNum = randomNumber(range);
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        int numKinds = (int) (maxKinds * Math.random()) + 2;// 至少出现两种数

        // 二. 把这些数填入数组中
        // 先填入 kTimeNum
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        HashSet<Integer> set = new HashSet<>();
        // 写入真命天子
        set.add(kTimeNum);
        for (; index < times; index++) {
            arr[index] = kTimeNum;
        }
        --numKinds;
        // 写入其他数
        while (numKinds != 0) {
            // 生成一个新的数
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            // 写入数组
            set.add(curNum);
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
            //更新 numKinds
            --numKinds;
        }

        // 三. 打乱数组顺序
        for (int i = 0; i < arr.length; i++) {
            int idx = (int) (Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }
        // 四. 返回数组
        return arr;
    }

    // [-range, +range]
    public static int randomNumber(int range) {
        return ((int) (range * Math.random()) + 1) -
                ((int) (range * Math.random()) + 1);
    }

    public static void main(String[] args) {
        int kinds = 4;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("开始 !");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1;
            int b = (int) (Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            //保证 k 和 m 在 1~9 之间
            if (k == m) {
                if (k == 9) {
                    --k;
                } else {
                    ++m;
                }
            }


            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
            if (ans2 != ans1) {
                System.out.println(ans1 + " != " + ans2);
                System.out.println(Arrays.toString(arr));
                System.out.println("出错!!");
                break;
            }
        }
        System.out.println("结束!");
    }
}
