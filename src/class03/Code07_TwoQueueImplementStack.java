package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/25 21:57
 * @desciption: 使用队列实现栈
 * 实现栈,先入后出
 */
public class Code07_TwoQueueImplementStack {
    public static class TwoQueueStack {
        public Queue<Integer> queue;
        public Queue<Integer> help;

        public TwoQueueStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(Integer value) {
            queue.offer(value);
        }

        public Integer poll() {
            // 倒数据到 help 队列,直到只剩下一个
            while (this.queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public Integer peek() {
            // 倒数据到 help 队列,直到只剩下一个
            while (this.queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            help.offer(ans);
            Queue<Integer> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack myStack = new TwoQueueStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");
    }
}
