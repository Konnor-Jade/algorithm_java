package class03;

import java.util.Stack;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/25 20:29
 * @desciption: 最小栈问题
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1） pop、push、getMin操作的时间复杂度都是 O（1）。
 * 2）设计的栈类型可以使用现成的栈结构。
 */
public class Code05_GetMinStack {
    // 思路: 用两个栈,一个栈正常,另一个为最小栈
    /*
    MyStack1
    条件压栈：只有当新加入的元素小于或等于当前最小值时，才会将这个新元素压入stackMin。

    条件弹栈：只有当stackData弹出的元素等于stackMin的栈顶元素时，才会将stackMin的栈顶元素弹出。
    空间效率：这种方法使得stackMin可能不会存储每个状态下的最小值，因此在某些情况下，它比MyStack2更节省空间。

    MyStack2
    无条件压栈：无论如何，每次调用push方法时，都会向stackMin压入一个元素。
    如果新加入的元素大于当前最小值，则重复压入当前的最小值。
    这样做确保了stackMin总是与stackData保持相同的元素数量，每个状态下的最小值都有一个直接的记录
    无条件弹栈：每次调用pop方法时，stackMin和stackData都会同步弹出栈顶元素。
    这意味着stackMin始终记录了对应于stackData每个状态下的最小值。
    空间效率：由于stackMin可能会存储大量重复的最小值，这种方法的空间效率低于MyStack1。
     */
    public static class MyStack1 {
        Stack<Integer> stackData;
        Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        //条件压栈：只有当新加入的元素小于或等于当前最小值时，才会将这个新元素压入stackMin。
        public void push(Integer value) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(value);
            } else if (this.stackMin.peek() >= value) {
                this.stackMin.push(value);
            }
            this.stackData.push(value);
        }

        // 条件弹栈:只有当stackData弹出的元素等于stackMin的栈顶元素时，才会将stackMin的栈顶元素弹出
        public Integer pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("空栈, 不能弹出");
            }
            if (this.stackMin.peek() == this.stackData.peek()) {
                this.stackMin.pop();
            }
            return this.stackData.pop();
        }

        public Integer getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("空栈, 没有最小值");
            }
            return this.stackMin.peek();
        }
    }

    public static class MyStack2 {
        Stack<Integer> stackData;
        Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        // 无条件压栈
        public void push(Integer value) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(value);
            } else if (this.stackMin.peek() > value) {
                this.stackMin.push(value);
            } else {
                this.stackMin.push(this.stackMin.peek());
            }
            this.stackData.push(value);
        }

        public Integer pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("空栈, 不能弹出了!");
            } this.stackMin.pop();
            return this.stackData.pop();
        }

        public Integer getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("空栈, 没有最小值");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }

}
