package class03;

import java.util.Stack;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/25 21:27
 * @desciption: 栈和队列的相互实现
 * 宽度优先遍历用队列实现,  深度优先遍历用栈实现
 * 实现队列,先进先出 add poll peek
 */
public class Code06_TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            this.stackPop = new Stack<>();
            this.stackPush = new Stack<>();
        }

        // push 栈 倒数据 到 pop 栈
        public void pushToPop() {
            // 只有当 pop 栈空了才能倒数据
            if (this.stackPop.isEmpty()) {
                while (!this.stackPush.isEmpty()) {
                    stackPop.push(this.stackPush.pop());
                }
            }
        }

        public void add(Integer value) {
            this.stackPush.push(value);
            pushToPop();
        }

        public Integer poll() {
            if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
                throw new RuntimeException("队列空了");
            }
            pushToPop();
            return this.stackPop.pop();
        }

        public Integer peek() {
            if (this.stackPop.isEmpty() && this.stackPush.isEmpty()) {
                throw new RuntimeException("队列空了");
            }
            pushToPop();
            return this.stackPop.peek();
        }

        public static void main(String[] args) {
            TwoStacksQueue test = new TwoStacksQueue();
            test.add(1);
            test.add(2);
            test.add(3);
            System.out.println(test.peek());
            System.out.println(test.poll());
            System.out.println(test.peek());
            System.out.println(test.poll());
            System.out.println(test.peek());
            System.out.println(test.poll());
        }


    }
}
