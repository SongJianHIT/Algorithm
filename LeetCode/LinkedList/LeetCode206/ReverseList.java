/**
 * @projectName Algorithm
 * @package LinkedList.LeetCode206
 * @className LinkedList.LeetCode206.ReverseList
 */
package LinkedList.LeetCode206;

/**
 * ReverseList
 * @description 206. 反转链表
 * @author SongJian
 * @date 2022/11/25 08:38
 * @version
 */
public class ReverseList {

   private class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public ListNode reverseList(ListNode head) {
        // 递归法
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newhead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newhead;
    }
    public ListNode reverseList_2(ListNode head) {
        // 迭代法
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode reverseList_1(ListNode head) {
        // 头插法反转链表
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode();
        ListNode tmp = head, tmp1 = res;
        while (tmp != null) {
            ListNode add = new ListNode(tmp.val);
            tmp1 = res.next;
            res.next = add;
            add.next = tmp1;
            tmp = tmp.next;
        }
        return res.next;
    }
}
 
