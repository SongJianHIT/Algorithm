/**
 * @projectName Algorithm
 * @package data_structures.linkedlist
 * @className data_structures.linkedlist.Partition
 */
package data_structures.linkedlist;

/**
 * Partition
 * @description
 * @author SongJian
 * @date 2022/12/1 10:25
 * @version
 */
public class Partition {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 将链表按照划分值划分为三个区域，不使用容器方法
     * @param head
     * @param pivot 划分值
     * @return
     */
    public static Node listPartition1(Node head, int pivot) {
        // small head
        Node sH = null;
        // small tail
        Node sT = null;
        // equal head
        Node eH = null;
        // equal tail
        Node eT = null;
        // big head
        Node mH = null;
        // big tail
        Node mT = null;
        // save next node
        Node next = null;
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) {
            // 如果有小于区域
            sT.next = eH;
            // 下一步，谁去连大于区域的头，谁就变成eT
            eT = eT == null ? sT : eT;
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) {
            // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    /**
     * 将链表按照划分值划分为三个区域，使用容器方法，用数组 partition
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        // 统计链表长度
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        // 创建数组
        Node[] nodeArr = new Node[i];
        // 存入数组
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        // 数组按值 partition
        arrPartition(nodeArr, pivot);
        // 分完区后前后相连
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /**
     * 数组按照值分区
     * @param nodeArr
     * @param pivot
     */
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

}
 
