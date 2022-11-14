/**
 * @projectName Algorithm
 * @package linked_list
 * @className linked_list.SingleLinkedList
 */
package data_structures.linked_list;

/**
 * SingleLinkedList
 * @description 单链表相关题目
 * @author SongJian
 * @date 2022/11/14 16:08
 * @version
 */
public class SingleLinkedList {
    /**
     * @title 单链表节点类
     * @author SongJian
     * @updateTime 2022/11/14 16:10
     * @throws
     * @description
     */
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int data) {
            value = data;
        }
    }

    /**
     * @title reverseLinkedList
     * @author SongJian
     * @param: node
     * @updateTime 2022/11/14 16:11
     * @return: linked_list.SingleLinkedList.Node
     * @throws
     * @description  反转单链表
     */
    public static ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * @title removeValue
     * @author SongJian
     * @param: head
     * @param: num
     * @updateTime 2022/11/14 16:44
     * @return: linked_list.SingleLinkedList.ListNode
     * @throws
     * @description 删除链表中特定值节点
     */
    public static ListNode removeValue(ListNode head, int num) {
        // 首先，将 head 移动到第一个不需要删的位置
        // 作为返回的 head
        while (head != null) {
            if (head.value != num) break;
            head = head.next;
        }
        // 1) 若 head == null，则不进下面的循环，直接返回
        // 2) 若 head != null，则进入该循环，删除内部特定值节点
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.value == num) {
                // 需要删除当前节点
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
 
