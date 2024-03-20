package class03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/19 22:41
 * @desciption: 反转链表
 */
public class Code01_ReverseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
            this.next = null;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int data) {
            this.value = data;
            this.next = null;
            this.last = null;
        }
    }

    /**
     * Reverse single linked list
     *
     * @param head Head node
     * @return reversed single linked list header node
     */
    public static Node reverseLinkedList(Node head) {
        //  head
        //   a    ->   b    ->  c  ->  null
        //   c    ->   b    ->  a  ->  null
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        // 循环最后截止时,pre 指向原始最后一个节点,head 指向null
        // 这个时候需要返回最后一个节点也就是 pre
        return pre;
    }


    /**
     * Reverse Double Linked List
     *
     * @param head DoubleLinkedList Head Node
     * @return reversed Double Linked List header node
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        //  head
        //   a    ->   b    ->  c  ->  null
        //   c    ->   b    ->  a  ->  null
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }


    /**
     * Using containers to reverse a single linked list
     *
     * @param head singleLinkedList Header Node
     * @return reversed singleLinkedList header node
     */
    public static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }

        ArrayList<Node> list = new ArrayList<>();
        // 遍历链表
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // 将头结点设置为空
        list.get(0).next = null;
        // 遍历 list 将next指针反转
        int N = list.size();
        for (int i = 1; i < N; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);
    }

    public static DoubleNode testReverseDoubleLinkedList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();
        // 遍历链表
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // 单独处理原始头结点
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        // 遍历 list 将next指针反转
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;// 当前节点的 last 交给下个循环的节点处理
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }


    /**
     * 生成随机单链表
     *
     * @param len   链表最大长度
     * @param value 链表节点的 value 最大值是多少
     * @return 单链表的头结点
     */
    public static Node generateRandomLinkedList(int len, int value) {
        // 确定链表长度
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        // 逐个生成链表节点,并链接
        Node head = new Node((int) (Math.random() * (value + 1)));
        --size;
        Node pre = head;
        while (size > 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            --size;
        }
        // 返回头结点
        return head;
    }

    public static DoubleNode generateRandomDoubleLinkedList(int len, int value) {
        // 确定节点长度
        int size = (int) (Math.random() * (len + 1));
        // 判断节点长度
        if (size == 0) {
            return null;
        }
        // 处理头结点
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        --size;
        // 生成后续所有节点
        DoubleNode pre = head;
        while (size > 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            cur.last = pre;
            pre.next = cur;
            pre = cur;
            --size;
        }
        // 返回所有节点
        return head;
    }

    /**
     * 将链表的原始顺序保存到数组中
     *
     * @param head 链表头结点
     * @return 数组
     */
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    /**
     * 将链表的原始顺序保存到数组中
     *
     * @param head 链表头结点
     * @return 数组
     */
    public static List<Integer> getDoubleLinkedListOriginOrder(DoubleNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    // TODO: 2024/3/19 8 验证单链表和双链表是否正确反转
    public static boolean checkReverseLinkedList(Node head, List<Integer> list) {
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            Integer integer =  list.get(i);
            if (!integer.equals(head.value)){
                return false;
            }
            head = head.next;
        }
        return true;
    }
    public static boolean checkReverseDoubleLinkedList(DoubleNode head, List<Integer> list) {
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            Integer integer =  list.get(i);
            if (!integer.equals(head.value)){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // TODO: 2024/3/19 main函数
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            Node head1 = generateRandomLinkedList(len, value);
            List<Integer> list1 = getLinkedListOriginOrder(head1);
            head1 = reverseLinkedList(head1);
            if (!checkReverseLinkedList(head1, list1)) {
                System.out.println("wrong 1!");
            }

            Node head2 = generateRandomLinkedList(len, value);
            List<Integer> list2 = getLinkedListOriginOrder(head2);
            head2 = testReverseLinkedList(head2);
            if (!checkReverseLinkedList(head2, list2)) {
                System.out.println("wrong 2!");
            }

            DoubleNode head3 = generateRandomDoubleLinkedList(len, value);
            List<Integer> list3 = getDoubleLinkedListOriginOrder(head3);
            head3 = reverseDoubleLinkedList(head3);
            if (!checkReverseDoubleLinkedList(head3, list3)) {
                System.out.println("wrong 3!");
            }

            DoubleNode head4 = generateRandomDoubleLinkedList(len, value);
            List<Integer> list4 = getDoubleLinkedListOriginOrder(head4);
            head4 = testReverseDoubleLinkedList(head4);
            if (!checkReverseDoubleLinkedList(head4, list4)) {
                System.out.println("wrong 4!");
            }
            
        }
        System.out.println("test finish!");
    }


}
