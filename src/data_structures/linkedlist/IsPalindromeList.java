/**
 * @projectName Algorithm
 * @package data_structures.linkedlist
 * @className data_structures.linkedlist.IsPalindromeList
 */
package data_structures.linkedlist;

import java.util.Stack;

/**
 * IsPalindromeList
 * @description 回文链表
 * @author SongJian
 * @date 2022/11/30 10:29
 * @version
 */
public class IsPalindromeList {

    // 单链表
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用容器（栈）的结构进行判断
     * @return
     */
    public static boolean isPalindromeList_1(Node head) {
        // 栈
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        // 第一次遍历，入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 第二次遍历，弹栈匹配
        while (head != null) {
            if (head.value != stack.pop().value) {
                // 不匹配
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 不使用容器，空间复杂度为 O(1) 的方法
     * @param head
     * @return
     */
    public static boolean isPalindromeList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node p1 = head;
        Node p2 = head;
        // 快慢指针找中点
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        // 完成后，p1 为中点
        // 后一半进行逆序，p3 辅助指针
        Node p3 = null;
        p2 = p1.next;
        p1.next = p3;
        while (p2 != null) {
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        // 完成后，p1 为后半部链表的head
        // p3 保存该head，用来最后的恢复
        p3 = p1;
        // p2 为原始链表头
        p2 = head;
        boolean res = true;
        while (p1 != null && p2 != null) {
            if (p1.value != p2.value) {
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 最后，恢复链表
        p1 = p3.next;
        p3.next = null;
        while (p1 != null) {
            p2 = p1.next;
            p1.next = p3;
            p3 = p1;
            p1 = p2;
        }
        return res;
    }
}
 
