package class03;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/22 21:49
 * @desciption: 数组实现栈和队列
 */
public class Code04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int pulli;
        private int size;
        private final int limit;


        public MyQueue(int limit) {
            this.arr = new int[limit];
            this.pushi = 0;
            this.pulli = 0;
            this.size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了,不能再加了");
            }
            size++;
            arr[pulli] = value;
            pulli = nextIndex(pulli);
        }

        public int pull() {
            if (size == 0) {
                throw new RuntimeException("队列空了,不能再拿了");
            }
            size--;
            int ans = arr[pulli];
            pulli = nextIndex(pulli);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

}
