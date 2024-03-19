package class02;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/19 08:39
 * @desciption: arr 中,找出出现奇数次的数
 */
public class Code02_EvenTimesOddTimes {
    // arr 中,只有一种数出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr 中,只有 2 种数出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        // 1 假设两种数是 a 和 b,第一步求出 a^b
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 2 提取出最右侧的 1
        // eor & (-eor)或者eor & (~eor +1);
        int rightOne = eor & (-eor);
        // 3 遍历找到 a 或者 b 其中一个
        int findOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                // 找到了,退出
                findOne = arr[i];
                break;

                /*
                onlyOne ^= arr[i];
                这种写法没有 break,把所有数分为两类,一类包括 a,一类包括 b
                这样走过一轮 for 循环就能提取出 a 或者 b
                 */
            }
        }

        System.out.println("a = " + findOne + ", b = " + (eor ^ findOne));
    }

    // 找出 bit 中 1 的个数
    public static int bit1counts(int N) {
        int count = 0;
        while (N != 0) {
            // 找到 最右侧的 1, count++
            int rightOne = N & (~N + 1);
            ++count;
            // 然后去掉最右侧的 1,开始下一轮统计
            N ^= rightOne;
        }
        return count;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a + " " + b);
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 2, 1, 1, 1};
        printOddTimesNum1(arr1);//应该输出 3

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);// 应该输出 3 和 2
    }
}
