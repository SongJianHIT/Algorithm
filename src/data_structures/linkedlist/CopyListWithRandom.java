/**
 * @projectName Algorithm
 * @package data_structures.linkedlist
 * @className data_structures.linkedlist.CopyListWithRandom
 */
package data_structures.linkedlist;

import javax.crypto.NoSuchPaddingException;
import java.util.HashMap;

/**
 * CopyListWithRandom
 * @description
 * @author SongJian
 * @date 2022/12/1 11:06
 * @version
 */
public class CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node random;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用容器的方式进行复制
     * @param head
     * @return
     */
    public Node copyRandomList(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 不使用容器的方式进行复制
     * @param head
     * @return
     */
    public Node copyRandomList1(Node head){
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
 
