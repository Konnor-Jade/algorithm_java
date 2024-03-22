package class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: algorithm_java
 * @author: Konnor https://github.com/Konnor-Jade
 * @date: 2024/3/22 19:32
 * @desciption: 在链表中删除给定的数的节点
 */
public class Code02_DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
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
         * Delete the node of a given value in the linked list
         *
         * @param head Head node of linked list
         * @param num  The number to be deleted
         * @return The header node of the new linked list of the node that deletes these values
         */
        public static Node removeValue(Node head, int num) {
            // 移动到第一个不需要删除的节点之前
            while (head != null) {
                if (head.value != num) {
                    break;
                }
                head = head.next;
            }

            // 遍历链表,删除目标节点
            Node pre = head;
            Node cur = head;
            while (cur != null) {
                // 判断节点是不是需要删除
                if (cur.value == num) {
                    pre.next = cur.next;
                } else {
                    pre = cur;
                }
                // 移动 cur 和 pre 指针
                cur = cur.next;
            }
            // 返回头结点
            return head;
        }

        public static DoubleNode removeValue(DoubleNode head, int num) {
            // 移动到第一个不需要删除的节点之前
            while (head != null && head.value == num) {
                head = head.next;
            }
            if (head != null) {
                head.last = null;
            } else {
                return null;
            }

            // 遍历链表,删除目标节点
            DoubleNode pre = head;
            DoubleNode cur = head.next;
            while (cur != null) {
                // 判断节点是不是需要删除
                if (cur.value == num) {
                    pre.next = cur.next;
                    if (cur.next != null) {
                        cur.next.last = pre;
                    }
                } else {
                    pre = cur;
                }
                // 移动 cur 和 pre 指针
                cur = cur.next;
            }
//            System.out.println("num = " + num);
//            printLinkedList(head);
            // 返回头结点
            return head;
        }

        /**
         * 将链表的原始顺序排除 num 后保存到数组中
         *
         * @param head 链表头结点
         * @param num  需要排除的数字
         * @return 数组
         */
        public static List<Integer> getLinkedListOriginOrder(Node head, int num) {
            List<Integer> list = new ArrayList<>();
            while (head != null) {
                if (head.value != num) {
                    list.add(head.value);
                }
                head = head.next;
            }
            return list;
        }

        public static List<Integer> getLinkedListOriginOrder(DoubleNode head, int num) {
            List<Integer> list = new ArrayList<>();
            while (head != null) {
                if (head.value != num) {
                    list.add(head.value);
                }
                head = head.next;
            }
            return list;
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

        public static boolean checkLinkedList(Node head, List<Integer> list) {
            for (Integer integer : list) {
                if (!integer.equals(head.value)) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        public static boolean checkLinkedList(DoubleNode head, List<Integer> list) {
            for (Integer integer : list) {
                if (!integer.equals(head.value)) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        public static void main(String[] args) {
            int len = 50;
            int value = 5;
            int testTime = 100000;
            System.out.println("测试开始");
            for (int i = 0; i < testTime; i++) {
                int numToDelete = (int) (Math.random() * (value + 1));

                Node head1 = generateRandomLinkedList(len, value);
                List<Integer> list1 = getLinkedListOriginOrder(head1, numToDelete);
                head1 = removeValue(head1, numToDelete);
                if (!checkLinkedList(head1, list1)) {
                    System.out.println("wrong!");
                    break;
                }

                DoubleNode head2 = generateRandomDoubleLinkedList(len, value);
                List<Integer> list2 = getLinkedListOriginOrder(head2, numToDelete);
//                System.out.println("numToDelete = " + numToDelete);
//                System.out.println(list2.toString());
                head2 = removeValue(head2, numToDelete);
//                printLinkedList(head2);
                if (!checkLinkedList(head2, list2)) {
                    System.out.println("wrong! 2");
                    break;
                }
            }
            System.out.println("测试结束!");
        }

        private static void printLinkedList(DoubleNode head2) {
            List<Integer> list = new ArrayList<>();
            while (head2!=null){
                list.add(head2.value);
                System.out.print(head2.value + ", ");
                head2 = head2.next;
            }
            System.out.println();
            System.out.println(list.toString());
        }
    }
}
