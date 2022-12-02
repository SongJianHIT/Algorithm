/**
 * @projectName Algorithm
 * @package data_structures.linkedlist
 * @className data_structures.linkedlist.FindFirstIntersectNode
 */
package data_structures.linkedlist;

/**
 * FindFirstIntersectNode
 *
 * @author SongJian
 * @description 找到两条链表的相交节点
 * @date 2022/12/2 10:23
 */
public class FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 找到两个链表的第一个相交节点
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            // 情况一
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            // 情况三
            return bothLoop(head1, head2, loop1, loop2);
        }
        // 情况二
        return null;
    }




    /**
     * 已知 head1， head2 无环，求 head1 和 head2 的相交节点；不相交则放回 null
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 从头开始走
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        // 统计链表1长度
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        // 此时cur1走到了链表1的最后一个节点，n记录了链表1的长度
        while (cur2.next != null) {
            n--;
            cur2 = cur1.next;
        }
        // 此时cur2走到了链表2的最后一个节点
        // 如果n为正数，则说明链表1更长，否则链表2更长
        if (cur1 != cur2) {
            // 如果尾节点不相等
            // 不可能相交
            return null;
        }
        // 哪个链表长，就把长的给 cur1
        cur1 = n > 0 ? head1 : head2;
        // 哪个链表短，就把短的给 cur2
        cur2 = cur1 == head1 ? head2 : head1;
        // cur1必定先走
        n = Math.abs(n);
        // 长的链表先走差值
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        // 一起遍历
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 已知 head1 和 head2 均有环，返回第一个相交的节点，如果不相交则返回 null
     * @param head1
     * @param head2
     * @param loop1
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        if (loop1 == loop2) {
            // 子情况2
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1.next != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 如果loop1!=loop2
            // 对应子情况1和子情况3
            cur1 = loop1.next;
            while (cur1 != loop1) {
                // 遍历 loop1
                if (cur1 == loop2) {
                    // 找到了，则匹配子情况3
                    return loop1;
                }
                cur1 = cur1.next;
            }
        }
        // 不相交
        return null;
    }

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
 
