/**
 * @projectName Algorithm
 * @package data_structures.linkedlist
 * @className data_structures.linkedlist.FindFirstIntersectNode
 */
package data_structures.linkedlist;

/**
 * FindFirstIntersectNode
 * @description 找到第一个入环节点
 * @author SongJian
 * @date 2022/12/2 09:56
 * @version
 */
public class GetLoopNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 找到链表的第一个入环节点
     * 如果无环，则返回 null
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 快慢指针
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                // 快指针结束，必无环
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // 相遇后，fast回到head，一次走一步
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        // 再次相遇，即入环节点
        return fast;
    }
}
 
